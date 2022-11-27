package com.stg.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.entity.RationAdmin;
import com.stg.entity.RationCard;
import com.stg.entity.RationCardMember;
import com.stg.exception.RationApplicationException;

import com.stg.repository.RationCardMemberRepository;
import com.stg.repository.RationCardRepository;
import com.stg.service.RationCardMemberService;

@Service
public class RationCardMemberImpl implements RationCardMemberService {

	@Autowired
	private RationCardMemberRepository rationCardMemberRepository;

	@Autowired
	private RationCardRepository rationCardRepository;

	@Override
	public RationCardMember addRationCardMember(RationCardMember rationCardMember, long rationCardNo)
			throws RationApplicationException {
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNo);
		if (rationCardMember != null) {
			rationCardMember.setRationCard(rationCard);
			return rationCardMemberRepository.save(rationCardMember);
		} else {
			throw new RationApplicationException("can't able to create");
		}
	}

	@Override
	public RationCardMember readRationCardMember(int memberId) throws RationApplicationException {
		if (rationCardMemberRepository.existsById(memberId)) {
			return rationCardMemberRepository.findById(memberId).get();
		} else {
			throw new RationApplicationException("MemberId is Not found");
		}

	}

	@Override
	public RationCardMember updateRationCardMember(int memberId, int age) throws RationApplicationException {
		RationCardMember rationCardMember = null;
		if (rationCardMemberRepository.existsById(memberId)) {
			Optional<RationCardMember> optional = rationCardMemberRepository.findById(memberId);
			optional.get().setAge(age);
			rationCardMember = optional.get();
			rationCardMemberRepository.save(rationCardMember);
			return rationCardMember;
		} else {
			throw new RationApplicationException("Unable to Update");
		}
	}

	@Override
	public String deleteRationCardMember(int memberId) throws RationApplicationException {
		if (rationCardMemberRepository.existsById(memberId)) {
			rationCardMemberRepository.deleteById(memberId);
			return "Deleted Succesfully";
		} else {
			throw new RationApplicationException("Unable to delete");
		}
	}

	@Override
	public String UpdateRationCardMemberToRationCard(int rationId, int rationCardmemberId)
			throws RationApplicationException {

		RationCard rationCard = rationCardRepository.findById(rationId).get();
		RationCardMember rationCardMember = rationCardMemberRepository.findById(rationCardmemberId).get();
		rationCardMember.setRationCard(rationCard);
		rationCardMemberRepository.save(rationCardMember);
		if (rationCard != null) {
			return "Updated Successfully";
		} else {
			throw new RationApplicationException("Ration Id is incorrect");
		}

	}

}
