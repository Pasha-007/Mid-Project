package com.example.pomodoro.controller;
import com.example.pomodoro.Task;
import com.example.pomodoro.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Display list of tasks on the main page
    @GetMapping("/")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "main";
    }

    // Display form for creating a new task
    @GetMapping("/add")
    public String addTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "addTask";
    }

    // Save new task to the database
    @PostMapping("/add")
    public String addTask(@RequestBody Task task, BindingResult result) {
        System.out.println("xzfzf\n\n\n");
        if (result.hasErrors()) {
            return "addTask";
        }
        taskRepository.save(task);
        return "redirect:/";
    }

    // Display form for editing an existing task
    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            model.addAttribute("task", optionalTask.get());
            return "editTask";
        } else {
            return "redirect:/";
        }
    }

    // Update an existing task in the database
    @PostMapping("/{id}/edit")
    public String editTask(@PathVariable Long id, @Validated Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "editTask";
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task updatedTask = optionalTask.get();
            updatedTask.setName(task.getName());
            updatedTask.setComplete(task.isComplete());
            taskRepository.save(updatedTask);
        }
        return "redirect:/";
    }

    // Display confirmation form for deleting a task
    @GetMapping("/{id}/delete")
    public String deleteTaskForm(@PathVariable Long id, Model model) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            model.addAttribute("task", optionalTask.get());
            return "deleteTask";
        } else {
            return "redirect:/";
        }
    }

    // Delete a task from the database
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.delete(optionalTask.get());
        }
        return "redirect:/";
    }

}
