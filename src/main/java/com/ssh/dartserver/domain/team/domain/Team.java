package com.ssh.dartserver.domain.team.domain;

import com.ssh.dartserver.domain.proposal.domain.Proposal;
import com.ssh.dartserver.domain.university.domain.University;
import com.ssh.dartserver.global.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Team extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private TeamUsersCombinationHash teamUsersCombinationHash;

    @Column(name = "is_visible_to_same_university")
    private Boolean isVisibleToSameUniversity;

    @Embedded
    private ViewCount viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private University university;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamUser> teamUsers = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamRegion> teamRegions = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @BatchSize(size = 500)
    private List<SingleTeamFriend> singleTeamFriends = new ArrayList<>();

    @OneToMany(mappedBy = "requestedTeam")
    private List<Proposal> requestedTeamProposals = new ArrayList<>();

    @Builder
    public Team(String name, Boolean isVisibleToSameUniversity, University university, TeamUsersCombinationHash teamUsersCombinationHash) {
        this.name = Name.from(name);
        this.isVisibleToSameUniversity = isVisibleToSameUniversity;
        this.university = university;
        this.teamUsersCombinationHash = teamUsersCombinationHash;
        this.viewCount = ViewCount.from(0);
    }

    public void update(String name, Boolean isVisibleToSameUniversity, TeamUsersCombinationHash teamUsersCombinationHash) {
        this.name = Name.from(name);
        this.isVisibleToSameUniversity = isVisibleToSameUniversity;
        this.teamUsersCombinationHash = teamUsersCombinationHash;
    }

    public void increaseViewCount(int viewCountIncrement) {
        this.viewCount = this.viewCount.increase(viewCountIncrement);
    }
}
