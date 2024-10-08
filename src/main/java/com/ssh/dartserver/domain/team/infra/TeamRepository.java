package com.ssh.dartserver.domain.team.infra;

import com.ssh.dartserver.domain.team.domain.Team;
import com.ssh.dartserver.domain.team.domain.TeamUsersCombinationHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {
    Optional<Team> findByTeamUsersCombinationHash(TeamUsersCombinationHash teamUsersCombinationHashValue);

    @Query("select t from Team t where concat('-', t.teamUsersCombinationHash.value, '-') like :userIdPattern " )
    List<Team> findAllTeamByUserIdPattern(@Param("userIdPattern") String userIdPattern);

    @Modifying
    @Query("update Team t " +
            "set t.viewCount.value = t.viewCount.value + :viewCountIncrement " +
            "where t in :teams")
    void increaseAllTeamViewCount(@Param("teams") List<Team> teams, @Param("viewCountIncrement") int viewCountIncrement);
}
