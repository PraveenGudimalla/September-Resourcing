/**
  *	
  *@author:Praveen Gudimalla
  *
  *
  **/

package com.resourcing.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.resourcing.beans.Candidate;
import com.resourcing.beans.Employee;
import com.resourcing.controller.CandidateController;
import com.resourcing.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {

	static Logger LOGGER = Logger.getLogger(CandidateController.class);
	
	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void addCandidate(Candidate candidate) {
		this.candidateRepository.save(candidate);

	}

	@Override
	public List<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
	}

	@Override
	public Candidate getCandidateById(int candidateId) {
		return candidateRepository.findById(candidateId).get();
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		candidateRepository.save(candidate);

	}

	@Override
	public void deleteCandidateById(int CandidateId) {
		this.candidateRepository.deleteById(CandidateId);

	}

	@Override
	public Candidate getCandidate(String email, String password) {
		return candidateRepository.findByEmailIgnoreCaseAndPassword(email, password);

	}

	@Override
	public Candidate findByEmail(String email) {
		return candidateRepository.findByEmail(email);
	}


	@Override
	public List<Candidate> newCandidatesList() {
		return candidateRepository.newCandidatesList();
	}

	@Override
	public List<Candidate> getCandidateListOfRecruiter(Employee employee) {
		return candidateRepository.getCandidateListOfRecruiter(employee);
	}

	@Override
	public List<Candidate> getassignedCandidateList() {
		return candidateRepository.getassignedCandidateList();
	}

	@Override
	public String sendSimpleEmail(String toEmail, String body, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("resourcingproject360@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		javaMailSender.send(message);
		LOGGER.debug("Mail Sent...");
		return "successfulRegistration";
	}


}
