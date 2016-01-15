//
//  LoginViewController.swift
//  TravelTips
//
//  Created by Teng on 12/28/15.
//  Copyright © 2015 huoteng. All rights reserved.
//  登录页面

import UIKit

class LoginViewController: UIViewController {
    
    @IBOutlet weak var logoImg: UIImageView!
    
    @IBOutlet weak var userNameTextField: UITextField!
    @IBOutlet weak var passWordTextField: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    
    // Customer UI
    let spinner = UIActivityIndicatorView(activityIndicatorStyle: .WhiteLarge)
    let warningMessage = UIImageView(image: UIImage(named: "Warning"))
    var loginPosition = CGPoint.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
       
        
        self.logoImg.center.x -= self.view.bounds.width
        
        let paddingViewForUsername = UIView(frame: CGRectMake(0, 0, 44, self.userNameTextField.frame.height))
        self.userNameTextField.leftView = paddingViewForUsername
        self.userNameTextField.leftViewMode = .Always
        
        let paddingViewForPassword = UIView(frame: CGRectMake(0, 0, 44, self.passWordTextField.frame.height))
        self.passWordTextField.leftView = paddingViewForPassword
        self.passWordTextField.leftViewMode = .Always
        
        let userImageView = UIImageView(image: UIImage(named: "User"))
        userImageView.frame.origin = CGPoint(x: 13, y: 10)
        self.userNameTextField.addSubview(userImageView)
        
        let passwordImageView = UIImageView(image: UIImage(named: "Key"))
        passwordImageView.frame.origin = CGPoint(x: 12, y: 9)
        self.passWordTextField.addSubview(passwordImageView)
        
        self.userNameTextField.center.x -= self.view.bounds.width
        self.passWordTextField.center.x -= self.view.bounds.width
        
        self.loginPosition = self.loginButton.center
        self.loginButton.center.x -= self.view.bounds.width
        
        self.view.addSubview(self.warningMessage)
        self.warningMessage.hidden = true
        self.warningMessage.center = self.loginPosition
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)

        
        UIView.animateWithDuration(0.5, delay: 0.5, usingSpringWithDamping: 0.6, initialSpringVelocity: 0.5, options: [], animations: {
            self.logoImg.center.x += self.view.bounds.width
            }, completion: nil)
        
      
        UIView.animateWithDuration(0.4, delay: 0.6, options: .CurveEaseOut, animations: {
            self.userNameTextField.center.x += self.view.bounds.width
            }, completion: nil)
        
        UIView.animateWithDuration(0.4, delay: 0.7, options: .CurveEaseOut, animations: {
            self.passWordTextField.center.x += self.view.bounds.width
            }, completion: nil)
        
        UIView.animateWithDuration(0.4, delay: 0.8, options: .CurveEaseOut, animations: {
            self.loginButton.center.x += self.view.bounds.width
            }, completion: nil)
        
    }
    
  
    @IBAction func logInTapped(sender: AnyObject) {
        
        let username = userNameTextField.text
        let password = passWordTextField.text
        self.loginButton.addSubview(self.spinner)
        self.spinner.frame.origin = CGPointMake(12, 12)
        self.spinner.startAnimating()
        if let name = username  {
            if  let pass = password {
                if name == "123" && pass == "123"{
                    
                }else{
                    UIView.transitionWithView(self.warningMessage,
                        duration: 0.3,
                        options: .TransitionFlipFromTop,
                        animations: {
                            self.warningMessage.hidden = true
                        }, completion: nil)
                    
                    UIView.animateWithDuration(0.3, animations: {
                        self.loginButton.center = self.loginPosition
                        }, completion: { _ in
                            self.loginButton.center.x -= 30
                            UIView.animateWithDuration(1.5, delay: 0, usingSpringWithDamping: 0.2, initialSpringVelocity: 0, options: [], animations: {
                                self.loginButton.center.x += 30
                                }, completion: {finished in
                                    UIView.animateWithDuration(0.3, animations: {
                                        self.loginButton.center.y += 90
                                        self.spinner.removeFromSuperview()
                                        }, completion: { _ in
                                            UIView.transitionWithView(self.warningMessage,
                                                duration: 0.3,
                                                options: [.TransitionFlipFromTop, .CurveEaseOut],
                                                animations: {
                                                    self.warningMessage.hidden = false
                                                }, completion: nil)
                                    })
                            })
                    })
                }
            }
        }
        
        

    }
    
    
    
}
