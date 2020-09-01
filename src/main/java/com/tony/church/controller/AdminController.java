package com.tony.church.controller;


import com.tony.church.component.Details;
import com.tony.church.entity.*;
import com.tony.church.model.Mail;
import com.tony.church.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class AdminController {

	@Autowired
	ChurchEventService churchEventService;

	@Autowired
	MemberService memberService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	TitheDetailService titheDetailService;

	@Autowired
	private ErrorAttributes errorAttributes;

	@Autowired
	private AppUserService appUserService;

	@Autowired
	AppRoleService appRoleService;

	@GetMapping("/logout")
	public String logout(){

		return "fancy-login";
	}

	@GetMapping("/")
	public String showHome(Model model) {

		return "redirect:/index";
	}
	
	// add request mapping for /leaders  this is not required

	@GetMapping("/events")
	public String getAllEvents(HttpServletRequest request, Model model){

		int page = 0; //default page number is 0
		int size = 8; //default page size is 10

		if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			page = Integer.parseInt(request.getParameter("page")) - 1;
		}

		if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			size = Integer.parseInt(request.getParameter("size"));
		}

		Page<ChurchEvent> events = churchEventService.findAll(PageRequest.of(page, size));
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
	public String allMembers(HttpServletRequest request, Model model){

		int page = 0; //default page number is 0
		int size = 8; //default page size is 10

		if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			page = Integer.parseInt(request.getParameter("page")) - 1;
		}

		if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			size = Integer.parseInt(request.getParameter("size"));
		}


		Page<Member> members = memberService.findAll(PageRequest.of(page, size));
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
	public String saveMember(@ModelAttribute @Valid Member member, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>!!!!!!!!!!!>>>>>>>>>> BINDING RESULT ERROR");
			model.addAttribute("member", member);
			model.addAttribute("address",member.getAddress());
			return "member-form";
		}
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
			if(!departmentId.equals(""))
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

	@GetMapping("/members/member/tithe_details")
	public String memberTitheHistory(@RequestParam("memberId") Integer id, Model model){
		Member member = memberService.findById(id);
		model.addAttribute("member",member);
		return "tithe-details";
	}

	@GetMapping("/members/member/show_update_tithe")
	public String showUpdateTitheForm(@RequestParam("titheId") int theId, Model model) {
		//get the tithe
		TitheDetail tithe = titheDetailService.findById(theId);
		//System.err.println(tithe);
		model.addAttribute("tithe", tithe);
		model.addAttribute("member", tithe.getMember());
		return "tithe-form";
	}

	@GetMapping("/members/member/show_add_tithe")
	public String showAddTitheForm(@RequestParam("memberId") int theId, Model model){
		Member member = memberService.findById(theId);
		TitheDetail titheDetail = new TitheDetail();
		model.addAttribute("tithe", titheDetail);
		model.addAttribute("member", member);
		return "tithe-form";
	}

	@PostMapping("/members/member/save_tithe")
	public String saveMemberTithe( @ModelAttribute TitheDetail tithe, @RequestParam("memberId") Integer mId){
		if(tithe.getId() == null)
			tithe.setId(0);

		Member member = memberService.findById(mId);
		if(member != null) {
			tithe.setMember(member);
			titheDetailService.save(tithe);
		}

		//System.err.println(">>>>>>>>>>>>>>>>>>>>>>> " + tithe + ">>>>>>>>>>> Mid: "+ mId);

		return "redirect:/members/member/tithe_details?memberId=" + mId +"";
	}

	@GetMapping("/members/member/delete_tithe")
	public String deleteSingleTitheEntry(@RequestParam("titheId") Integer id) {
		TitheDetail titheDetail = titheDetailService.findById(id);

		if(titheDetail != null)
			titheDetailService.remove(titheDetail);

		return "redirect:/members/member/tithe_details?memberId=" + titheDetail.getMember().getId() + "";
	}

	@GetMapping("/users/show_update_user")
	public String showUpdateUserForm(@RequestParam("userId") int theId, Model model) {
		AppUser user = appUserService.findById(theId);
		user.setSelectedRoles(appUserService.findUserRoles(user.getAppUserName()));
		String roles="";
		for(String role : user.getSelectedRoles()){
			roles += user.getAppUserName()+";"+role+ ",";
		}
		user.setPrevSelectedRoles(roles);
		model.addAttribute("appUser", user);
		model.addAttribute("userRoles",user.getRoles());
		return "user-form";
	}

	@GetMapping("/users")
	public String allUsers(Model model){

		List<AppUser> users = appUserService.findAll();
		model.addAttribute("appUsers",users);

		return "users";
	}

	@GetMapping("/users/show_add_user")
	public String showAddUserForm(Model model){
		AppUser appUser = new AppUser();
		System.err.println(appUser.getRoles());
		//appUser.getSelectedRoles().add(appUser.getRoles().get(0));
		model.addAttribute("appUser", appUser);
		model.addAttribute("userRoles",appUser.getRoles());
		return "user-form";
	}

	@PostMapping("/users/save_user")
	public String saveUser(@ModelAttribute AppUser user){

		String strPrevUserRoles = user.getPrevSelectedRoles();


		// only if user exists and has roles already attached
		if(strPrevUserRoles.contains(",")) {
			System.err.println(">>>>>>>>>>>>>>>>> "+strPrevUserRoles);
			String[] prevRolesArray = strPrevUserRoles.split(",");
			List<String> toRemoveOld = new ArrayList<>();
			List<String> toRemoveNew = new ArrayList<>();
			List<String> prevRolesList = new LinkedList<>(Arrays.asList(prevRolesArray));
			for (String prevRole : prevRolesList) {
				for (String newRole : user.getSelectedRoles()) {
					if (prevRole.equals(user.getAppUserName() + ";" + newRole)) {
						toRemoveOld.add(prevRole);
						toRemoveNew.add(newRole);
					}
				}
			}
			//System.err.println(">>>>>>>>>>>>>>>>> Prev "+strPrevUserRoles);
			System.err.println(">>>>>>>>>>>>>>>>> remove " + toRemoveOld);
			prevRolesList.removeAll(toRemoveOld);
			user.getSelectedRoles().removeAll(toRemoveNew);
			System.err.println(">>>>>>>>>>>>>>>>> To Delete " + prevRolesList);

			for (String prevRole : prevRolesList) {
				String[] oldUserCred = prevRole.split(";");
				appRoleService.removeByUsernameAndRole(oldUserCred[0], oldUserCred[1]);
			}
		}
		System.err.println(">>>>>>>>>>>>>>>>> To add " + user.getSelectedRoles());

		user.setAppPassword("{noop}"+user.getAppPassword());
		if(user.getId() == null)
			user.setId(0);
		user.setEnabled(true);
		for (String strRole: user.getSelectedRoles()) {
			AppRole role = new AppRole();
			role.setAppUserName(user.getAppUserName());
			role.setAppAuthority(strRole);
			appRoleService.save(role);
		}
		appUserService.save(user);

		return "redirect:/users";
	}

	@GetMapping("/index")
	public String index(){
		return "index";
	}

}










