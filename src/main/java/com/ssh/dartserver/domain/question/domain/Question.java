package com.ssh.dartserver.domain.question.domain;

import com.ssh.dartserver.global.common.BaseTimeEntity;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private QuestionCategory category;

    @NotNull
    @Column(unique = true)
    private String content;

    private String icon;

    private boolean enabled;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TargetGender targetGender;

    @Builder
    public Question(String content, String icon, boolean enabled, TargetGender targetGender) {
        this.content = content;
        this.icon = icon;
        this.enabled = enabled;
        this.targetGender = targetGender;
    }
}
