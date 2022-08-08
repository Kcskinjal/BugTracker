package com.BugTracker.Service;

import java.util.List;

import com.BugTracker.Entity.Bug;
import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.User;

import org.springframework.stereotype.Service;

@Service
public interface BugService {
	
	Bug saveBug(Bug bug);
	
	List<Bug> getAllBugs();
	
	Bug getBugById(Long id);
	
	void deleteBugById(Long id);
	
	List<Bug> findAllByProject(Project project);
	
	List<Bug> findAllByTester_Id(User user);

	List<Bug> findAllByDeveloper(User user);
	
		
	
	

}
