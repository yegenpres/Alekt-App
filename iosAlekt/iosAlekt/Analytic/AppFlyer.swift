//
//  AppFlyer.swift
//  iosAlekt
//
//  Created by 111 on 07.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import AppsFlyerLib
import AppTrackingTransparency
import shared

class AppFlyer {
    private var key: String {
        if let path = Bundle.main.path(forResource: "Config", ofType: "plist") {
            if let dict = NSDictionary(contentsOfFile: path) as? [String: AnyObject] {
                if let stringValue = dict["AppFlyer"] as? String {
                    return stringValue
                }
            }
        }
       fatalError("no key for RevenueCat")
    }
    
    private var bundleId: String {
        if let path = Bundle.main.path(forResource: "info", ofType: "plist") {
            if let dict = NSDictionary(contentsOfFile: path) as? [String: AnyObject] {
                if let stringValue = dict["RevenueKay"] as? String {
                    return stringValue
                }
            }
        }
        return "no key for RevenueCat"
    }

    func initService(waitToInit seconds: TimeInterval, isDebug: Bool) {
        AppsFlyerLib.shared().appsFlyerDevKey = key
        AppsFlyerLib.shared().appleAppID = bundleId
        AppsFlyerLib.shared().start()
        AppsFlyerLib.shared().isDebug = isDebug
        AppsFlyerLib.shared().waitForATTUserAuthorization(timeoutInterval: seconds)
    }
    
    func logIn(userID: String) {
        AppsFlyerLib.shared().customerUserID = userID
    }
    
    static func askPermit() {
        if #available(iOS 14, *) {
             ATTrackingManager.requestTrackingAuthorization { (status) in
               switch status {
               case .denied:
                   print("AuthorizationSatus is denied")
               case .notDetermined:
                   print("AuthorizationSatus is notDetermined")
               case .restricted:
                   print("AuthorizationSatus is restricted")
               case .authorized:
                   print("AuthorizationSatus is authorized")
               @unknown default:
                   fatalError("Invalid authorization status")
               }
             }
           }
    }
    
    func trackPurcahse(offer: SubscriptionProduct) {
        AppsFlyerLib.shared().logEvent(AFEventPurchase,
        withValues: [
            AFEventParamContentType: offer.type,
            AFEventParamRevenue: offer.title,
            AFEventParamCurrency: offer.price
        ])
    }

}
