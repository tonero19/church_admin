package com.tony.church.controller;


import com.tony.church.component.Details;
import com.tony.church.entity.Address;
import com.tony.church.entity.ChurchEvent;
import com.tony.church.entity.Department;
import com.tony.church.entity.Member;
import com.tony.church.model.Mail;
import com.tony.church.service.ChurchEventService;
import com.tony.church.service.DepartmentService;
import com.tony.church.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

	@Autowired
	ChurchEventService churchEventService;

	@Autowired
	MemberService memberService;

	@Autowired
	DepartmentService departmentService;

	/*@Autowired
	public DemoController(ChurchServiceImpl churchService) {
		this.churchService = churchService;
	}*/

	@GetMapping("/")
	public String showHome(Model model) {

		//List<ChurchEvent> events = churchService.findAll();
		//for(ChurchEvent event : events)
		//ChurchEvent churchEvent = new ChurchEvent();
		//model.addAttribute("churchEvent", churchEvent);

		return "home";
	}
	
	// add request mapping for /leaders  this is not required

	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}

	@GetMapping("/events")
	public String getAllEvents(Model model){

		List<ChurchEvent> events = churchEventService.findAll();
		model.addAttribute("events",events);

		return "events";
	}

	@GetMapping("/events/show_update_event")
	public String showUpdateEventForm(@RequestParam("eventId") int theId, Model model){
		//get the event
		ChurchEvent churchEvent = churchEventService.findById(theId);
		model.addAttribute("churchEvent", churchEvent);
		return "event-form";
	}

	@GetMapping("/events/show_add_event")
	public String showAddEventForm(Model model){
		ChurchEvent churchEvent = new ChurchEvent();
		model.addAttribute("churchEvent", churchEvent);
		return "event-form";
	}

	@PostMapping("/events/save_event")
	public String addEvent(@ModelAttribute ChurchEvent churchEvent, Model model){

		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>!!!!!!!!!!!>>>>>>>>>> "+churchEvent.getId());
		churchEventService.save(churchEvent);

		return "redirect:/events";
	}

	@GetMapping("/events/delete_event")
	public String removeEvent(@RequestParam("eventId") int id){

		churchEventService.remove(id);
		return "redirect:/events";
	}

	@GetMapping("/members")
	public String allMembers(Model model){

		List<Member> members = memberService.findAll();
		model.addAttribute("members",members);

		return "members";
	}

	@GetMapping("/members/details")
	public String getMemberExtendedDetails(@RequestParam("memberId") int id, Model model){

		Member member = memberService.findById(id);
		List<Department> allDepartments = departmentService.findAll();
		Details details = new Details();
		details.setMemberId(id);
		String ids = "";

		Set<Department> memberDepartments = member.getDepartments();
		for(Department memberDept : memberDepartments){
			details.getSelectedDepartments().add(memberDept.getId());
			ids += memberDept.getId() + ",";
		}
		ids = ids.substring(0,ids.length());
		details.setIdsAsSingleString(ids);

		/*List<String[]> checkBoxData = new ArrayList<>();
		for(Department department: allDepartments){
			boolean flag = false;
			for(Department memberDept : memberDepartments){
				if(department.getId() == memberDept.getId())
					flag = true;
			}
			checkBoxData.add(new String[]{department.getId()+"", flag+"", department.getDepartmentName()});
		}*/

		model.addAttribute("member", member);
		model.addAttribute("allDepartments", allDepartments);
		model.addAttribute("details", details);
		//model.addAttribute("checkBoxData",checkBoxData);

		return "member-details";
	}

	//show_member_update_form
	@GetMapping("/members/member_update")
	public String showUpdateMemberForm(@RequestParam("memberId") int theId, Model model){
		//get the member
		Member member = memberService.findById(theId);
		model.addAttribute("member", member);
		model.addAttribute("address",member.getAddress());
		return "member-form";
	}

	//show_member_add_form
	@GetMapping("/members/member_add")
	public String showUpdateMemberForm(Model model){

		Member member = new Member();
		member.setAddress(new Address());
		model.addAttribute("member", member);
		model.addAttribute("address",member.getAddress());
		return "member-form";
	}

	@PostMapping("/members/save_member")
	public String saveMember(@ModelAttribute Member member, Model model){

		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>!!!!!!!!!!!>>>>>>>>>> "+member);
		memberService.save(member);

		return "redirect:/members";
	}


	@GetMapping("/members/save_member_departments")
	public String saveMemberDepartments(@ModelAttribute Details details){

		System.out.println(">>>>>!!!!!>>>> MemberId: " + details.getMemberId());
		Member member = memberService.findById(details.getMemberId());
		String[] ids = details.getIdsAsSingleString().split(",");
		// First remove all member departments
		Set<Department> memberDepartments = member.getDepartments();
		System.err.println(">>>>>>>>!!!!!!!!!!!!!>>>>>>>> member dept ids " + details.getIdsAsSingleString());
		for (String departmentId : ids) {
			System.err.println(">>>>>>>>!!!!!!!!!!!!!>>>>>>>> deptId =  " + departmentId);
			member.removeDepartment(departmentService.findById(Integer.parseInt(departmentId)));
		}
		//System.err.println(">>>>>>>>!!!!!!!!!!!!!>>>>>>>> member dept size after " + details.getOldSelectedDepartments().size());

		// Then add all new member departments
		for (Integer departmentId : details.getSelectedDepartments()) {
			//System.out.println(">>>>>>>>!!!!!!!!!!!!!>>>>>>>>  " + departmentId);
			member.addDepartment(departmentService.findById(departmentId));
		}

		//update the database
		memberService.save(member);

		return "redirect:/members";
	}

	@GetMapping("/members/delete_member")
	public String removeMember(@RequestParam("memberId") int id){

		memberService.remove(id);
		return "redirect:/members";
	}

	@RequestMapping(value = "members/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> search(@RequestParam(value="term", required = false, defaultValue = "") String term) {
		return memberService.search(term);
	}


	@GetMapping("/send-email")
	public String showEmailForm(Model model){
		Mail mail = new Mail();
		model.addAttribute("mail",mail);

		return "email-form";

	}
}










