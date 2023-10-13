package com.ssh.dartserver.domain.chat.presentation;

import com.ssh.dartserver.domain.chat.domain.ChatRoomUser;
import com.ssh.dartserver.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {

    @Query("select cru " +
            "from ChatRoomUser cru " +
            "join fetch cru.chatRoom cr " +
            "join fetch cru.user u " +
            "where u = :user")
    List<ChatRoomUser> findAllByUser(@Param("user") User user);

    @Query("select cru " +
            "from ChatRoomUser cru " +
            "join fetch cru.chatRoom cr " +
            "join fetch cru.user u " +
            "where cr.id = :chatRoomId")
    List<ChatRoomUser> findAllByChatRoomId(@Param("chatRoomId") Long chatRoomId);

    @Query("select cru " +
            "from ChatRoomUser cru " +
            "join fetch cru.chatRoom cr " +
            "join fetch cr.proposal p " +
            "where p.requestingTeam.id = :teamId or p.requestedTeam.id = :teamId")
    List<ChatRoomUser> findAllByTeamId(@Param("teamId") Long teamId);

    void deleteByUserIdAndChatRoomId(Long userId, Long chatRoomId);
}
