package br.ufba.softvis.visminer.repository.local.git;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;

import br.ufba.softvis.visminer.model.database.Commit;
import br.ufba.softvis.visminer.model.database.File;
import br.ufba.softvis.visminer.model.database.FileState;
import br.ufba.softvis.visminer.model.database.FileStatePK;
import br.ufba.softvis.visminer.model.database.Repository;
import br.ufba.softvis.visminer.persistence.FileDAO;
import br.ufba.softvis.visminer.persistence.FileStateDAO;
import br.ufba.softvis.visminer.util.StringUtils;

public class FilesUpdater {

	public static void save(IRepositorySystem repositorySystem, List<Commit> commits, Repository repository) throws RevisionSyntaxException, MissingObjectException, IncorrectObjectTypeException, AmbiguousObjectException, IOException{
		List<File>filesByCommit = new ArrayList<File>();
		List<File>filesAux = new ArrayList<File>();
		List<File> files = new ArrayList<File>();
		FileDAO fileDAO = new FileDAO();

		for(Commit commit : commits){
			List<String> names = repositorySystem.getFiles(commit.getUid());
			
			for(String name : names){
				
				String[] parts = name.split("/");
				int i;
				String path;
				File parent = null;
				
				for(i = 0; i < parts.length - 1; i++){
					
					path = parent != null ? parent.getPath()+"/"+parts[i] : repository.getPath()+"/"+parts[i];
					File child = new File(StringUtils.sha1(path), true, parts[i], path, parent);
					parent = getNextParent(child, filesByCommit, filesAux, files);
					
				}
				
				path = parent != null ? parent.getPath()+"/"+parts[i] : repository.getPath()+"/"+parts[i];
				File child = new File(StringUtils.sha1(path), false, parts[i], path, parent);
				getNextParent(child, filesByCommit, filesAux, files);
				
			}
			
			fileDAO.batchSave(filesAux);
			filesStatesPersist(commit, filesByCommit);
		}
	}
	
	private static File getNextParent(File child, List<File> filesByCommit, List<File> filesAux, List<File> files){
		int index = filesByCommit.indexOf(child);
		if(index > -1){
			return filesByCommit.get(index);
		}
		
		index = files.indexOf(child);
		if(index > -1){
			filesByCommit.add(files.get(index));
			return files.get(index);
		}
		
		filesAux.add(child);
		filesByCommit.add(child);
		files.add(child);
		
		return child;
	}

	
	private static void filesStatesPersist(Commit commit, List<File> files){
		List<FileState> fileStates = new ArrayList<FileState>();
		FileStateDAO fileStateDAO = new FileStateDAO();
		
		for(File file : files){
			FileState fs = new FileState(new FileStatePK(file.getId(), commit.getId()));
			fileStates.add(fs);
		}

		fileStateDAO.batchSave(fileStates);
	}
	
}
