package com.mou.election.service;

import com.mou.election.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmessageService {

    EResult add(EmessageDTO mesageDto, Set<String> userAttributes);

    EResult delById(EmessageDTO mesageDto);

    EResult update(EmessageDTO mesageDto);

    List<EmessageDTO> queryById(String id, EPageResult page);

    EResult reply(EuserMessageDTO userMessageDTO);

    Map<String,Object> replyRead(String userAttr, EPageResult page);

    List<EuserMessageJoinUserDTO> replyShow(EuserMessageDTO euserMessageDTO, EPageResult page);

    List<EuserMessageDTO> replyShow(EPageResult page, String messageId, String userAttri);

}
