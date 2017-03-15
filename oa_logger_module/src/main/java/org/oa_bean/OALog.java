package org.oa_bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_log")
public class OALog {
 private Integer id;
 private String actiontime;
 private String actiondescribe;
 @Id
 @GeneratedValue
 public Integer getId() {
		return id;
 }
 public void setId(Integer id) {
		this.id = id;
 }
 public String getActiontime() {
		return actiontime;
 }
 public void setActiontime(String actiontime) {
		this.actiontime = actiontime;
 }
 public String getActiondescribe() {
		return actiondescribe;
 }
 public void setActiondescribe(String actiondescribe) {
		this.actiondescribe = actiondescribe;
 }
}
