package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Operator implements Serializable{
	private static final long serialVersionUID = 1L;



	@Id
	Long idOperateur;
	String fname;
	String lname;
	String password;
	@OneToMany
	@JsonIgnore
	Set<Invoice> invoices;
	
}
