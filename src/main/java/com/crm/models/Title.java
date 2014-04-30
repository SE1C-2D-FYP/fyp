/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Cliff
 */
@Entity
@Table(name = "TITLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Title.findAll", query = "SELECT t FROM Title t"),
    @NamedQuery(name = "Title.findByTitleId", query = "SELECT t FROM Title t WHERE t.titleId = :titleId"),
    @NamedQuery(name = "Title.findByEngTitle", query = "SELECT t FROM Title t WHERE t.engTitle = :engTitle"),
    @NamedQuery(name = "Title.findByChiTitle", query = "SELECT t FROM Title t WHERE t.chiTitle = :chiTitle"),
    @NamedQuery(name = "Title.findByTitlePoint", query = "SELECT t FROM Title t WHERE t.titlePoint = :titlePoint")})
public class Title implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TITLE_ID")
    private String titleId;
    @Size(max = 50)
    @Column(name = "ENG_TITLE")
    private String engTitle;
    @Size(max = 50)
    @Column(name = "CHI_TITLE")
    private String chiTitle;
    @Column(name = "TITLE_POINT")
    private Long titlePoint;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titleId")
    private Collection<Employee> employeeCollection;

    public Title() {
    }

    public Title(String titleId) {
        this.titleId = titleId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getEngTitle() {
        return engTitle;
    }

    public void setEngTitle(String engTitle) {
        this.engTitle = engTitle;
    }

    public String getChiTitle() {
        return chiTitle;
    }

    public void setChiTitle(String chiTitle) {
        this.chiTitle = chiTitle;
    }

    public Long getTitlePoint() {
        return titlePoint;
    }

    public void setTitlePoint(Long titlePoint) {
        this.titlePoint = titlePoint;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titleId != null ? titleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Title)) {
            return false;
        }
        Title other = (Title) object;
        if ((this.titleId == null && other.titleId != null) || (this.titleId != null && !this.titleId.equals(other.titleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm.models.Title[ titleId=" + titleId + " ]";
    }
    
}
//package testModels;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//public class CustomUserDetailsService   {
//    
//    
//}
//@Service
//@Transactional(readOnly=true)
//public class CustomUserDetailsService implements UserDetailsService {
//	
//	@Autowired
//	private UserDAO userDAO;	
//
//	public UserDetails loadUserByUsername(String login)
//			throws UsernameNotFoundException {
//		
//		com.sprsec.model.User domainUser = userDAO.getUser(login);
//		
//		boolean enabled = true;
//		boolean accountNonExpired = true;
//		boolean credentialsNonExpired = true;
//		boolean accountNonLocked = true;
//
//		return new User(
//				domainUser.getLogin(), 
//				domainUser.getPassword(), 
//				enabled, 
//				accountNonExpired, 
//				credentialsNonExpired, 
//				accountNonLocked,
//				getAuthorities(domainUser.getRole().getId())
//		);
//	}
//	
//	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
//		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
//		return authList;
//	}
//	
//	public List<String> getRoles(Integer role) {
//
//		List<String> roles = new ArrayList<String>();
//
//		if (role.intValue() == 1) {
//			roles.add("ROLE_MODERATOR");
//			roles.add("ROLE_ADMIN");
//		} else if (role.intValue() == 2) {
//			roles.add("ROLE_MODERATOR");
//		}
//		return roles;
//	}
//	
//	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		
//		for (String role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
//		return authorities;
//	}
//
//}
