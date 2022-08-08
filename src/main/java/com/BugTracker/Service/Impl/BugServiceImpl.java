package com.BugTracker.Service.Impl;

import java.util.List;

import com.BugTracker.Entity.Bug;
import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.User;
import com.BugTracker.Repository.BugRepository;
import com.BugTracker.Service.BugService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugServiceImpl implements BugService{
	
	@Autowired
	private BugRepository bugRepository;

	@Override
	public Bug saveBug(Bug bug) {
		
		return bugRepository.save(bug);
	}

	@Override
	public List<Bug> getAllBugs() {
		
		return bugRepository.findAll();
	}

	@Override
	public Bug getBugById(Long id) {
		
		return bugRepository.findById(id).get();
	}

	@Override
	public void deleteBugById(Long id) {
		
		bugRepository.deleteById(id);
	}

	@Override
	public List<Bug> findAllByProject(Project project) {
		
		return bugRepository.findAllByPid(project);
	}

	@Override
	public List<Bug> findAllByTester_Id(User user) {
				return bugRepository.findAllByTester(user);
	}

	@Override
	public List<Bug> findAllByDeveloper(User user) {
		// TODO Auto-generated method stub
		return bugRepository.findAllByDeveloper(user);
	}

	
	
	

}
