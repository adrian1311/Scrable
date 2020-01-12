package edu.uclm.esi.apalabreitor.apalabreitor.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.codec.digest.DigestUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.uclm.esi.apalabreitor.apalabreitor.dao.PalabraRepository;
import edu.uclm.esi.apalabreitor.apalabreitor.dao.UserRepository;
import edu.uclm.esi.apalabreitor.apalabreitor.model.Match;
import edu.uclm.esi.apalabreitor.apalabreitor.model.User;
import edu.uclm.esi.apalabreitor.apalabreitor.web.exceptions.LoginException;


@RestController
public class WebController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PalabraRepository palabraRepo;
	
	private List<User> users = new ArrayList<>();
	private List<Match> pendingMatches = new ArrayList<>();
	public static ConcurrentHashMap<String, Match> inPlayMatches = new ConcurrentHashMap<>();
	
	@Autowired
	public void loadPalabrasRepo() {
		Manager.get().setPalabrasRepo(palabraRepo);
	}
	
	@PostMapping("/solicitarPartida")
	public String solicitarPartida(HttpSession session, @RequestParam(value="action") String action) throws Exception {
		
		if (session.getAttribute("user")==null)
			throw new Exception("Identifícate antes de jugar");
		User user = (User) session.getAttribute("user");
		if (action.equals("Nueva partida")) {
			Match match = new Match();
			match.setPlayerA(user);
			this.pendingMatches.add(match);
			JSONObject jso=new JSONObject();
			jso.put("type", "PARTIDA CREADA");
			jso.put("idPartida", match.getId());
			return jso.toString();
		} else if (action.equals("Unir a partida")) {
			if (this.pendingMatches.isEmpty())
				throw new Exception("No hay partidas pendientes. Crea una.");
			Match match = this.pendingMatches.remove(0);
			match.setPlayerB(user);
			inPlayMatches.put(match.getId(), match);
			JSONObject jso=new JSONObject();
			jso.put("type", "PARTIDA LISTA");
			jso.put("idPartida", match.getId());
			return jso.toString();
		}
		return null;
	}
	
	@RequestMapping("/listaUsuarios")
	public List<User> listaUsuarios() {
		return this.users;
	}
	
	@RequestMapping("/register")
	public void register(@RequestParam(value="userName") String userName, 
			@RequestParam(value="email") String email, 
			@RequestParam(value="pwd1") String pwd1, @RequestParam(value="pwd2") String pwd2) throws Exception {
		if (pwd1==null || pwd2==null)
			throw new Exception("Empty passwords");
		if (!pwd1.equals(pwd2))
			throw new Exception("Passwords do not match");
		if (userRepo.findById(userName).isPresent() || userRepo.findByEmail(email)!=null)
			throw new Exception("The user already exists");
		User user=new User();
		String passEncMD5=DigestUtils.md5Hex(pwd1);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPwd(passEncMD5);
		userRepo.save(user);
	}
	
	@RequestMapping(value="/login")
	public void login(HttpSession session, 
			@RequestParam(value="userName") String userName, 
			@RequestParam(value="pwd") String pwd,
			@RequestParam boolean withEmail) throws LoginException {
		User user;
		String passEncMD5=DigestUtils.md5Hex(pwd);
		if (withEmail)
			user=userRepo.findByEmail(userName);
		else
			user=userRepo.findById(userName).get();
		if (user!=null && user.getPwd().equals(passEncMD5)) {
			session.setAttribute("user", user);
			this.users.add(user);
		}
		else throw new LoginException();
	}
	
	@RequestMapping(value="/login2")
	public void login2(HttpSession session, 
			@RequestParam(value="userName") String userName,
			@RequestParam(value="Email") String Email) throws LoginException {
			User user;
			if (userRepo.findById(userName).isPresent()) {
				user=userRepo.findById(userName).get();
				session.setAttribute("user", user);
				this.users.add(user);
			}
			else {
				User NewUser=new User();
				NewUser.setEmail(Email);
				NewUser.setUserName(userName);
				NewUser.setPwd("12345");
				userRepo.save(NewUser);
				NewUser=userRepo.findById(userName).get();
				session.setAttribute("user", NewUser);
				this.users.add(NewUser);
			}

	}
	

	
	@RequestMapping("/salir")
	public void salir(HttpSession session) throws Exception {
		session.invalidate();
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception ex) {
		ModelAndView result = new ModelAndView();
		result.setViewName("respuesta");
		result.addObject("exception", ex);
		result.setStatus(HttpStatus.BAD_REQUEST);
	    return result;
	}

	
}
