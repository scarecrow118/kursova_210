package com.example.taxicompany.controller;

import com.example.taxicompany.entity.Car;
import com.example.taxicompany.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/driver-requests")
    @PreAuthorize("hasRole('ADMIN')")
    public String getDriverRequests(Model model) {
        Iterable<Car> carRequests = adminService.getAllRequests();
        model.addAttribute("carRequests", carRequests);
        return "account";
    }

    @GetMapping("/admin/has-requests")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public boolean hasDriverRequests() {
        return adminService.getAllRequests().iterator().hasNext();
    }

    @PostMapping("/admin/approve-driver")
    @PreAuthorize("hasRole('ADMIN')")
    public String approveDriver(@RequestParam Long carId) {
        adminService.approveDriver(carId);
        return "redirect:/account";
    }

    @PostMapping("/admin/reject-driver")
    @PreAuthorize("hasRole('ADMIN')")
    public String rejectDriver(@RequestParam Long carId) {
        adminService.rejectDriver(carId);
        return "redirect:/account";
    }
}