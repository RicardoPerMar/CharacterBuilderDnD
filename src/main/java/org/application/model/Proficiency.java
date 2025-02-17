package org.application.model;


import jakarta.persistence.*;

@Entity
@Table(name = "class_proficiencies")
public class Proficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private CharacterClass characterClass; // Esto para tener la relación bidireccional

    @Column(name = "proficiency_type")
    private String proficiencyType;

    @Column(name = "proficiency_description")
    private String proficiencyDescription;


    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProficiencyDescription() {
        return proficiencyDescription;
    }

    public void setProficiencyDescription(String proficiencyDescription) {
        this.proficiencyDescription = proficiencyDescription;
    }

    public String getProficiencyType() {
        return proficiencyType;
    }

    public void setProficiencyType(String proficiencyType) {
        this.proficiencyType = proficiencyType;
    }
}
