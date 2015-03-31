package org.visminer.core.analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.ufba.softvis.visminer.model.database.Commit;
import br.ufba.softvis.visminer.model.database.CommitedFile;
import br.ufba.softvis.visminer.model.database.CommitedFileHistory;
import br.ufba.softvis.visminer.model.database.CommitedFileHistoryPK;
import br.ufba.softvis.visminer.persistence.CommitedFileDAO;
import br.ufba.softvis.visminer.persistence.CommitedFileHistoryDAO;
import br.ufba.softvis.visminer.util.StringUtils;

public class CommitedFilesUpdater {

	public static void save(IRepositorySystem repositorySystem, List<Commit> commits) throws IOException{
		List<CommitedFile> commitedFilesListed = new ArrayList<CommitedFile>();
		List<CommitedFile> filesToInsert = new ArrayList<CommitedFile>();
		List<CommitedFileHistory> historyToInsert = new ArrayList<CommitedFileHistory>();
		
		CommitedFileDAO filesDao = new CommitedFileDAO();
		CommitedFileHistoryDAO historyDao = new CommitedFileHistoryDAO();
		
		for(Commit commit : commits){
			Map<String, Boolean> files = repositorySystem.getCommitedFiles(commit.getUid());
			
			for(Entry<String, Boolean> file : files.entrySet()){
				CommitedFile aux = new CommitedFile(StringUtils.sha1(file.getKey()), file.getKey());
				int index = commitedFilesListed.indexOf(aux);
				if(index > -1){
					CommitedFileHistory cfHistory = new CommitedFileHistory(commitedFilesListed.get(index), commit, file.getValue());
					historyToInsert.add(cfHistory);
				}else{
					CommitedFile aux2 = filesDao.get(aux.getUid());
					if(aux2 == null){
						filesToInsert.add(aux);
					}else{
						aux.setId(aux2.getId());
					}
					commitedFilesListed.add(aux);
					CommitedFileHistory cfHistory = new CommitedFileHistory(aux, commit, file.getValue());
					historyToInsert.add(cfHistory);
				}
			}
		}
		
		filesDao.batchSave(filesToInsert);
		
		for(CommitedFileHistory cfh : historyToInsert){
			cfh.setId(new CommitedFileHistoryPK(cfh.getCommitedFile().getId(), cfh.getCommit().getId()));
		}
		historyDao.batchSave(historyToInsert);
	}
	
}
