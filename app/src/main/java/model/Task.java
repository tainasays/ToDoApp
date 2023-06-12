/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.util.Date;
/**
 *
 * @author thayn
 */
public class Task {
       
    private int id;
    private int idProject;
    private String name;
    private String description;
    private String notes;
    private boolean completed;
    private Date deadline;
    private Date createdAt;
    private Date updatedAt;

    
     public Task() {
        this.completed = false;
        this.createdAt = new java.util.Date();
        this.updatedAt = new java.util.Date();
        
        //this.deadline = new java.util.Date();
    }
    
    public Task(int id, int idProject, String name, String description, String notes, boolean isCompleted, Date deadline, Date createdAt, Date updatedAt) {
        this.id = id;
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.completed = isCompleted;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

 // @Override
  // public String toString() {
    //   return "Task{" + "id=" + id + ", idProject=" + idProject + ", name=" + name + ", description=" + description + ", notes=" + notes + ", isCompleted=" + completed + ", deadline=" + deadline + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
 // }

    //public Object Deadline() {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   // }
    
    
}
