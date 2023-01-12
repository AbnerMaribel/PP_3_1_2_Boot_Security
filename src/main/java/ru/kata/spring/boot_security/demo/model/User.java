package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @NotEmpty(message = "Name not null")
   @Size(min = 1, max = 30, message = "Name between 1 or 30")
   @Column(name = "name")
   private String name;
   @NotEmpty(message = "Surname not null")
   @Size(min = 1, max = 30, message = "Surname between 1 or 30")
   @Column(name = "surname")
   private String surname;

   @Column(name = "password")
   private String password;

   @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
   private Set<Role> roles;

   public User() {

   }
   public User(String name, String surname) {
      this.name = name;
      this.surname = surname;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String getUsername() {
      return getName();
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }
   @Override
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }


   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return name.equals(user.name) && surname.equals(user.surname);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, surname);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", surname='" + surname + '\'' +
              ", password='" + password + '\'' +
              '}';
   }
}
