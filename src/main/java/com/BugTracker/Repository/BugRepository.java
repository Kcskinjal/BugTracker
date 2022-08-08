package com.BugTracker.Repository;

import java.util.List;

import com.BugTracker.Entity.Bug;
import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long>{
	List<Bug> findAllByPid(Project project);
	
	List<Bug> findAllByTester(User user);
	
	List<Bug> findAllByDeveloper(User user);
	
}
