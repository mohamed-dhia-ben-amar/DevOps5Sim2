package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.Id;

import javax.persistence.*;
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Operator implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idOperateur;
	String fname;
	String lname;
	String password;
	@OneToMany
	@JsonIgnore
	Set<Invoice> invoices;
	
}
