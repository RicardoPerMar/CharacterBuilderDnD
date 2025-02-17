package org.application.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "class_levels")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private CharacterClass characterClass;


    @Column(name = "level")
    private Integer level;

    @ElementCollection
    @CollectionTable(name = "level_features", joinColumns = @JoinColumn(name = "level_id"))
    @Column(name = "feature")
    private List<String> features;

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
