package com.coll.RestController;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.DAO.ProfilePictureDAO;
import com.WeChat.ProfilePicture;
import com.WeChat.UserDetails;

@RestController
public class ProfilePictureRestController {

	@Autowired
	ProfilePictureDAO picturedao;
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePic(@RequestParam(value="file") CommonsMultipartResolver fileUpload,HttpSession session)
	{
		UserDetails user=(UserDetails) session.getAttribute("UserDetails");
		
		if(user==null)
		{
			return new ResponseEntity<String>("Error Occured",HttpStatus.UNAUTHORIZED);
		}
		else
		{
			ProfilePicture profile=new ProfilePicture();
		    profile.setUsername(user.getUsername());
		    System.out.println(fileUpload.getByte().length);
		    profile.setImage(fileUpload.getByte());
		    picturedao.addProfilePicture(profile);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage/{username}")
	public @ResponseBody byte[] getProfilePicture(@PathVariable("username")String username)
	{
		ProfilePicture picture=picturedao.getProfilePicture(username);
		if(picture==null)
			return null;
		else
		return picture.getImage();
	}
}
