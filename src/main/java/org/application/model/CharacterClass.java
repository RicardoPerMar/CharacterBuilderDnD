package org.application.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "character_classes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CharacterClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hit_dice")
    private String hitDice;

    @OneToMany(mappedBy = "characterClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proficiency> proficiencies;

    @OneToMany(mappedBy = "characterClass", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("levels")
    private List<Level> level;

    @ElementCollection
    @JsonProperty("extraAttack")
    private List<Integer> levelExtraAttack;

    @ElementCollection
    private List<Integer> abilityScoreImprovement;

    @OneToMany(mappedBy = "characterClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Character> characters;

    public List<Integer> getAbilityScoreImprovement() {
        return abilityScoreImprovement;
    }

    public void setAbilityScoreImprovement(List<Integer> abilityScoreImprovement) {
        this.abilityScoreImprovement = abilityScoreImprovement;
    }

    public List<Integer> getExtraAttack() {
        return levelExtraAttack;
    }

    public void setExtraAttack(List<Integer> extraAttack) {
        this.levelExtraAttack = extraAttack;
    }

    public String getHitDice() {
        return hitDice;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Level> getLevel() {
        return level;
    }

    public void setLevel(List<Level> level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Proficiency> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(List<Proficiency> proficiencies) {
        this.proficiencies = proficiencies;
    }
}

