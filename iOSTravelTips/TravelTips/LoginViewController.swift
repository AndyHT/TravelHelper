//
//  LoginViewController.swift
//  TravelTips
//
//  Created by Teng on 12/28/15.
//  Copyright © 2015 huoteng. All rights reserved.
//  登录页面

import UIKit

class LoginViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var logoImg: UIImageView!
    
    @IBOutlet weak var userNameTextField: UITextField!
    @IBOutlet weak var passWordTextField: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    @IBOutlet weak var registerButton: UIButton!
    
    
    // Customer UI
    let spinner = UIActivityIndicatorView(activityIndicatorStyle: .White)
    let warningMessage = UIImageView(image: UIImage(named: "Warning"))
    var loginPosition = CGPoint.zero
    
    // store the login button's vertical position in the view
    var loginButtonPositionY:CGFloat = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
       
        self.userNameTextField.delegate = self
        self.passWordTextField.delegate = self
        
        self.logoImg.center.x -= self.view.bounds.width
        
        self.userNameTextField.center.x -= self.view.bounds.width
        self.passWordTextField.center.x -= self.view.bounds.width
        
        self.loginPosition = self.loginButton.center
        self.loginButton.center.x -= self.view.bounds.width
        self.registerButton.center.x -= self.view.bounds.width
        
        self.view.addSubview(self.warningMessage)
        self.warningMessage.hidden = true
        self.warningMessage.center = self.loginPosition
        
        loginButtonPositionY = self.loginButton.center.y
        
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
        
        UIView.animateWithDuration(0.4, delay: 0.9, options: .CurveEaseOut, animations: {
            self.registerButton.center.x += self.view.bounds.width
            }, completion: nil)
        
    }
    
    @IBAction func logInTapped(sender: AnyObject) {
        
        let username = userNameTextField.text
        let password = passWordTextField.text
        self.loginButton.addSubview(self.spinner)
        self.spinner.frame.origin = CGPointMake(9,7)
        self.spinner.startAnimating()
        if let name = username, pass = password  {
            ServerModel.login(name, withPass: pass.md5(), callback: { (sessionID) -> Void in
                if let session = sessionID {
                    //登录成功，跳转到首页
                    NSUserDefaults.standardUserDefaults().setObject(session, forKey: "sessionID")
                    
                    let nextView = self.storyboard!.instantiateViewControllerWithIdentifier("MainView") as! MainViewController
                    
                    self.presentViewController(nextView, animated: true, completion: nil)
                } else {
                    //登录失败
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
                                        self.loginButton.center.y += 80
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
            })
                
        }
        
        

    }
    
    func textFieldDidBeginEditing(textField: UITextField) {
        self.warningMessage.hidden = true
    }
    
    override func touchesEnded(touches: Set<UITouch>, withEvent event: UIEvent?) {
        userNameTextField.resignFirstResponder()
        passWordTextField.resignFirstResponder()
    }
    
    
}
