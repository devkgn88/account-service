package com.gn.accoount.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="account")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_no")
	private Long accountNo;

	@Column(name="account_id")
	private String accountId;

	@Column(name="account_pw")
	private String accountPw;

	@Column(name="account_name")
	private String accountName;
	
	@OneToOne
	@JoinColumn(name="team_no")
	private Team team;
	
	@CreationTimestamp
	@Column(updatable=false,name="reg_date")
	private LocalDateTime regDate;
	
	@UpdateTimestamp
	@Column(insertable = false,name="mod_date")
	private LocalDateTime modDate;
}
