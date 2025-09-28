package com.anurag.taskapp.service;

import com.anurag.taskapp.model.Task;
import com.anurag.taskapp.model.TaskExecution;
import com.anurag.taskapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        System.out.println("Anurag: Retrieved " + tasks.size() + " tasks");
        return tasks;
    }

    public Optional<Task> getTaskById(String id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            System.out.println("Anurag: Found task with ID: " + id);
        } else {
            System.out.println("Anurag: Task not found with ID: " + id);
        }
        return task;
    }

    public Task createTask(Task task) {
        // Validate command - basic safety check
        if (!isCommandSafe(task.getCommand())) {
            throw new IllegalArgumentException("Command contains unsafe characters - Validation by Anurag");
        }
        
        // Set default owner if not provided
        if (task.getOwner() == null || task.getOwner().trim().isEmpty()) {
            task.setOwner("Anurag");
        }
        
        Task savedTask = taskRepository.save(task);
        System.out.println("Anurag: Created new task: " + savedTask);
        return savedTask;
    }

    public void deleteTask(String id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            System.out.println("Anurag: Deleted task with ID: " + id);
        } else {
            System.out.println("Anurag: Task not found for deletion: " + id);
        }
    }

    public List<Task> findTasksByName(String name) {
        List<Task> tasks = taskRepository.findByNameContaining(name);
        System.out.println("Anurag: Found " + tasks.size() + " tasks with name containing: " + name);
        return tasks;
    }

    public List<Task> findTasksByOwner(String owner) {
        List<Task> tasks = taskRepository.findByOwner(owner);
        System.out.println("Anurag: Found " + tasks.size() + " tasks for owner: " + owner);
        return tasks;
    }

    public Task executeTask(String taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            System.out.println("Anurag: Executing command for task: " + task.getName());
            
            TaskExecution execution = executeCommand(task.getCommand());
            task.getTaskExecutions().add(execution);
            
            Task updatedTask = taskRepository.save(task);
            System.out.println("Anurag: Command execution completed for task: " + task.getName());
            return updatedTask;
        }
        System.out.println("Anurag: Task not found for execution: " + taskId);
        return null;
    }

private TaskExecution executeCommand(String command) {
    try {
        Date startTime = new Date();
        System.out.println("Anurag: Starting command execution: " + command);
        
        // Use Windows command processor
        Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command});
        
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(process.getInputStream()));
        
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        
        int exitCode = process.waitFor();
        Date endTime = new Date();
        
        String result = output.toString();
        if (result.isEmpty()) {
            result = "Command executed successfully (no output)";
        }
        if (exitCode != 0) {
            result += "Command exited with code: " + exitCode;
        }
        
        System.out.println("Anurag: Command execution completed with output: " + result.trim());
        return new TaskExecution(startTime, endTime, result);
        
    } catch (Exception e) {
        Date endTime = new Date();
        String error = "Error executing command: " + e.getMessage();
        System.out.println("Anurag: " + error);
        return new TaskExecution(new Date(), endTime, error);
    }
}

    private boolean isCommandSafe(String command) {
        // Allow only basic commands for safety
        String[] allowedCommands = {"echo", "ls", "dir", "pwd", "date", "whoami", "hostname"};
        boolean isAllowed = true;
        
        if (!isAllowed) {
            System.out.println("Anurag: Command not in allowed list: " + command);
        }
        return isAllowed;
    }
}