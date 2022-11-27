package com.stg.service;

import com.stg.entity.RationCardMember;
import com.stg.exception.RationApplicationException;

public interface RationCardMemberService {

	public RationCardMember addRationCardMember(RationCardMember rationCardMember,long rationCardNo) throws RationApplicationException;

	public RationCardMember readRationCardMember(int memberId) throws RationApplicationException;

	public RationCardMember updateRationCardMember(int memberId, int age) throws RationApplicationException;

	public String deleteRationCardMember(int memberId) throws RationApplicationException;

	public abstract String UpdateRationCardMemberToRationCard(int rationId, int rationCardmemberId)
			throws RationApplicationException;

}
