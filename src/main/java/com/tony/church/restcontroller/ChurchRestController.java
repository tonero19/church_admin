package com.tony.church.restcontroller;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tony.church.entity.*;
import com.tony.church.model.Mail;
import com.tony.church.service.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class ChurchRestController {

    @Autowired
    ChurchEventService churchEventService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    TitheDetailService titheDetailService;
    @Autowired
    MemberService memberService;
    @Autowired
    AppUserService appUserService;
    @Autowired
    AnalysesService analysesService;

    @Autowired
    EmailSenderService emailService;

    @Autowired
    AppRoleService appRoleService;

//    @GetMapping("/all_events")
//    public List<ChurchEvent> userInfo() {
//
//        List<ChurchEvent> events = churchEventService.findAll();
//
//        return events;
//
//    }

    @GetMapping("/events/single_event/{id}")
    public ChurchEvent getSingleEvent(@PathVariable("id") Integer id) {
        return churchEventService.findById(id);
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    @GetMapping("/tithes")
    public List<TitheDetail> getAllTithes() {
        return titheDetailService.findAll();
    }


  /*@GetMapping("/leaders")
    public String manager(){

        return "Welcome to the manager zone. Retreat in 2 weeks, get ready!!!";
    }

    @GetMapping("/systems")
    public String admin(){

        return "Welcome to the admin zone. You can do all you want!!!";
    }*/

    @PostMapping("church_events/add_church_event")
    public ChurchEvent addChurchEvent(@RequestBody ChurchEvent churchEvent) {
        // in case an id was provided
        churchEvent.setId(0);
        churchEventService.save(churchEvent);

        return churchEvent;

    }

    @PostMapping("departments/add_department")
    public Department addDepartment(@RequestBody Department department) {
        // in case an id was provided
        department.setId(0);
        departmentService.save(department);

        return department;

    }

    @PostMapping("tithes/add_tithe")
    public TitheDetail addTithe(@RequestBody TitheDetail titheDetail) {
        // in case an id was provided
        titheDetail.setId(0);
        titheDetailService.save(titheDetail);

        return titheDetail;

    }

//    @GetMapping("/rest/members")
//    public List<Member> getAllMembers() {
//
//        List<Member> members = memberService.findAll();
//
//        return members;
//
//    }

    @GetMapping("/members/{id}")
    public Member getAllMembers(@PathVariable("id") Integer id) {

        Member member = memberService.findById(id);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Size: " + member.getTitheDetails().size());
        return member;

    }

    @PutMapping("/members/update_member")
    public Member updateMember(@RequestBody Member member) {
        memberService.save(member);
        return member;
    }

    @PutMapping("/church_events/update_event")
    public ChurchEvent updateEvent(@RequestBody ChurchEvent churchEvent) {
        churchEventService.save(churchEvent);
        return churchEvent;
    }

    @PutMapping("/departments/update_department")
    public Department updateDepartment(@RequestBody Department department) {
        //System.out.println("<<<<<<<<<<<<<<<<<<<!!!!!!!!!!>>>>>>>>>>> Dept Name: "+ department.getDepartmentName());
        departmentService.save(department);
        return departmentService.findById(department.getId());
    }


    @PostMapping("members/add_member")
    public Member addMember(@RequestBody Member member) throws Exception {
        // in case an id was provided
        member.setId(0);

        Department dept = departmentService.findById(1);//new Department("choir");
        Department dept2 = departmentService.findById(2);//new Department("ushering");

        member.getDepartments().add(dept);
        member.getDepartments().add(dept2);

        memberService.save(member);

        return member;

    }

    @PostMapping("member_department/add/{mid}/{did}")
    public void addRowToJoinTable(@PathVariable Map<String, String> ids) {
        Member member = memberService.findById(Integer.parseInt(ids.get("mid")));
        Department dept = departmentService.findById(Integer.parseInt(ids.get("did")));
        if (member != null && dept != null) {

            // add the department for the member
            member.addDepartment(dept);

            //add member to database
            memberService.save(member);
        }
    }

    @PutMapping("member_department/remove")
    public void removeRowFromJoinTable() {
        Member member = memberService.findById(17);
        Department dept = departmentService.findById(4);
        Department dept2 = departmentService.findById(1);

        if (member != null && dept != null && dept2 != null) {
            // delete the row matching the parameters
            member.removeDepartment(dept);
            member.removeDepartment(dept2);

            //update the database
            memberService.save(member);
        }

    }

    @DeleteMapping("members/remove_member/{id}")
    public void deleteMember(@PathVariable("id") Integer id) {
        Member member = memberService.findById(id);
        if (member != null) {
            memberService.remove(member);
        }
    }

    @DeleteMapping("departments/remove_department/{id}")
    public void deleteDepartment(@PathVariable("id") Integer id) {
        Department department = departmentService.findById(id);
        if (department != null) {
            departmentService.remove(department);
        }
    }

    @PostMapping("members/member/{id}/add_tithe")
    public TitheDetail addMemberTithe(@PathVariable("id") Integer id, @RequestBody TitheDetail titheDetail) throws Exception {
        titheDetail.setId(0);
        Member member = memberService.findById(id);
        if (member != null) {
            titheDetail.setMember(member);
            titheDetailService.save(titheDetail);
        } else {
            throw new Exception("No such member exist!! ");
        }

        return titheDetail;
    }

    @PutMapping("members/member/{id}/update_tithe")
    public TitheDetail updateMemberTithe(@RequestBody TitheDetail titheDetail, @PathVariable("id") Integer id) throws Exception {
        Member member = memberService.findById(id);
        if (member != null) {
            titheDetail.setMember(member);
            titheDetailService.save(titheDetail);
        } else {
            throw new Exception("No such member exist!! ");
        }

        return titheDetail;
    }

    @DeleteMapping("members/tithe/delete/{id}")
    public void deleteSingleTitheEntry(@PathVariable("id") Integer id) throws Exception {
        TitheDetail titheDetail = titheDetailService.findById(id);
        if (titheDetail != null) {
            titheDetailService.remove(titheDetail);
        } else {
            throw new Exception("No such entry exist!! ");
        }

    }

    @PostMapping("/send-email")
    public void sendEmail(@ModelAttribute Mail mail, @RequestParam("files") MultipartFile[] files) throws IOException, MessagingException {
        System.out.println(">>>>>>!!!>>>>>>>>>>> START... Sending email");
        String[] receivers;

        mail.setAttachments(files);

        if (mail.getOption() == 1) {
            List<String> allMemberEmails = memberService.findAllEmails();
            receivers = allMemberEmails.stream().toArray(String[]::new);
            // System.out.println(">>>>>>>> Emails: " + allMemberEmails);
        } else if (mail.getOption() == 2) {
            List<String> allWorkersEmails = memberService.findWorkersEmails();
            receivers = allWorkersEmails.stream().toArray(String[]::new);
        } else {
            receivers = mail.getMailToListAsString().split(";");
        }
        //receivers.add("OneMail@email.com");
        //receivers.add("TwoMail@email.com");
        //Mail mail = new Mail();
        mail.setFrom("rccgagphamburg@email.com"); //replace with your desired email
        mail.setMailToArray(receivers); //replace with your desired email
        mail.setSubject(mail.getSubject());

        Map<String, Object> model = new HashMap<>();

        model.put("name", "Anthony");
        model.put("location", "Hamburg");
        model.put("sign", "Java Developer");
        mail.setProps(model);

        emailService.sendEmail(mail);
        System.out.println(">>>>>!!!!>>>>>>>>>>>>> END... Email sent success");
    }


    @GetMapping("/user/{username}")
    public List<String> getUserRoles(@PathVariable("username") String username) {
        return appUserService.findUserRoles(username);
    }

    @PostMapping("/user/add")
    public List<String> saveUserAndRoles(@RequestBody AppUser user) {
        user.setAppPassword("{noop}" + user.getAppPassword());
        user.setId(0);
        for (String strRole : user.getSelectedRoles()) {
            AppRole role = new AppRole();
            role.setAppUserName(user.getAppUserName());
            role.setAppAuthority(strRole);
            appRoleService.save(role);
        }
        appUserService.save(user);

        return appUserService.findUserRoles(user.getAppUserName());
    }

    @GetMapping("/members/check-email")
    public boolean checkEmailInDB(@RequestParam("email") String email) {
        // System.err.println(">>>>>>>>>>>>  "+email);
        List<String> emails = memberService.findByEmail(email);
        return !(emails != null && emails.size() > 0);

    }

    @GetMapping("/user/check-username")
    public boolean checkUsernameInDB(@RequestParam("username") String username) {
        return appUserService.usernameTaken(username);
    }

    @DeleteMapping("/user/delete")
    public void deleteUserRole() {
        appRoleService.removeByUsernameAndRole("tunthisisnew@yahoo.de", "ROLE_MEMBER");

    }

    @GetMapping("/come-in-index")
    public List<List<Object>> indexStart() {

        // monthly tithe income
        String titheQ = "  SELECT date_part('month', tithe_detail.td_date) AS m,\n" +
                "    date_part('year', tithe_detail.td_date) AS y,\n" +
                "    sum(tithe_detail.amount) AS tithe \n" +
                "   FROM tithe_detail \n" +
                "  GROUP BY (date_part('month', tithe_detail.td_date)), (date_part('year', tithe_detail.td_date))";
        List<Object[]> tithe = analysesService.nativeQuery(titheQ);
        //monthly offering income
        String offeringQ = "SELECT date_part('month', service.service_date) AS m,\n" +
                "    date_part('year', service.service_date) AS y,\n" +
                "    sum(service.offering_amount) AS offering\n" +
                "   FROM service\n" +
                "  GROUP BY (date_part('month', service.service_date)), (date_part('year', service.service_date))";
        List<Object[]> offering = analysesService.nativeQuery(offeringQ);

        //monthly total income
        String totalIncomeQ = "SELECT month(td_date) M, year(td_date) Y\n" +
                "  ,sum(amount) T \n" +
                "  FROM view_income \n" +
                "  group by month(td_date), year(td_date) \n" +
                "  order by year(td_date), month(td_date)";

       // List<Object[]> totalIncome = analysesService.nativeQuery(totalIncomeQ);

        LocalDate date = LocalDate.of(2020, 1, 1);
        List<LocalDate> monthsInYear = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            monthsInYear.add(date);
            date = date.plusMonths(1);
        }

        List<List<Object>> dataPoints = new ArrayList<>();
        List<Object> labels = new ArrayList<>();
        labels.add("Income Type");labels.add("Offering"); labels.add("Tithe");
        dataPoints.add(labels);

        for (int i = 1; i <= 12; i++) {
            List<Object> list = new ArrayList<>();
            for(int j = 0; j <=2; j++){
                list.add(0);
            }
            dataPoints.add(list);
        }

        int index = 1;

        // fill in the bar labels with months and year
        for (LocalDate currentDate : monthsInYear) {
            Month month = currentDate.getMonth();
            int year = currentDate.getYear();
            dataPoints.get(index++).set(0, month.name() + " " + year);
        }

        for (Object[] arr : offering){
            if((Double) arr[1] == 2020.0){
                Integer innerIndex = (int)Math.round((Double)arr[0]);
                dataPoints.get(innerIndex).set(1,arr[2]);
            }
        }

        for (Object[] arr : tithe) {
            //System.err.print(">>>>>>> tithe  "+arr[0]+" "+arr[1]+" "+arr[2]);
            if((Double) arr[1] == 2020.0) {
                Integer innerIndex = (int)Math.round((Double)arr[0]);
                dataPoints.get(innerIndex).set(2,arr[2]);
            }
        }

        return dataPoints;
    }

    @GetMapping("/workers-in-department")
    public List<Object[]> workerDepartmentCount() {

        List<List<Object>> dataPoints = new ArrayList<>();

        // get number of workers per department
        String workersPerDeptQ = " select dept_name, count(department_id) \n" +
                " from public.\"viewMemberDepartment\" \n" +
                " group by dept_name";
        List<Object[]> workersPerDept = analysesService.nativeQuery(workersPerDeptQ);
        Object[] heading = {"Department name", "number of Workers"};
        workersPerDept.add(0,heading);

        return workersPerDept;
    }
}

