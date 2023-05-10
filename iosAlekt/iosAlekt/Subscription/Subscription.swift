
//
//  Subscription.swift
//  iosAlectApp
//
//  Created by 111 on 06.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import RevenueCat
import shared

class Subscription: SubscriptionService {
    
    private let appFlyer: AppFlyer
    
    private var key: String {
        if let path = Bundle.main.path(forResource: "Config", ofType: "plist") {
            if let dict = NSDictionary(contentsOfFile: path) as? [String: AnyObject] {
                if let stringValue = dict["RevenueKay"] as? String {
                    return stringValue
                }
            }
        }
        fatalError("no key for RevenueCat")
    }

    private let entitlementId = "basic_motch"

    private var offers = [Package]()
    
    init(appFlyer: AppFlyer) {
        self.appFlyer = appFlyer
        self.offers = []
    }

    func setDebug(isDebug: Bool) {
        guard isDebug else { return }
        Purchases.logLevel = .debug
    }

    func doInit() {
        Purchases.configure(withAPIKey: key)
    }

    func doInit(userId: String) {
        Purchases.configure(withAPIKey: key, appUserID: userId)
    }

    func isSubscribed() async throws -> SubscriptionStatus {
        do {
            let info = try await Purchases.shared.customerInfo()
            
                if !info.entitlements.active.isEmpty {
                    return SubscriptionStatus.IsSubscribed()
                } else {
                    return SubscriptionStatus.NotSubscribed()
                }
        } catch {
            return SubscriptionStatus.Err(err: error.localizedDescription)
        }
    }
    
    func fetchOffers() async throws -> Set<SubscriptionProduct> {
        
        do {
            let offers = try await Purchases.shared.offerings()
                        
            if let packages = offers.current?.availablePackages {
                self.offers = packages
                
                return Set(packages.map {  $0.toBase() })
            }
            
            print("offerings is empty")
            return Set()
            
        } catch {
            print(error.localizedDescription)
            return Set()
        }
    }
   

    func logIn(userId: String) async throws -> KotlinBoolean {
        do {
            let success = try await Purchases.shared.logIn(userId)
            
            return KotlinBoolean(bool: success.created)
        } catch {
            print(error)
            return false
        }
    }

    func subscribe(context: Any, productBase: SubscriptionProduct) async throws -> SubscriptionStatus {
            let package = offers.first { $0.storeProduct.localizedTitle == productBase.title}
        
            guard let package else {
                return SubscriptionStatus.Err(err: "package not fpond")
            }
            
        do {
            let result = try await Purchases.shared.purchase(package: package)
            
            if result.customerInfo.entitlements[self.entitlementId]?.isActive == true {
                appFlyer.trackPurcahse(offer: productBase)
                return SubscriptionStatus.IsSubscribed()
          }
            
            return SubscriptionStatus.Err(err: "purcahses can not subscribe")
        } catch {
            return SubscriptionStatus.Err(err: error.localizedDescription)
        }
    }
}

extension Package {
    func toBase() -> SubscriptionProduct {
        var type = PackageTypeBase.unknown

        switch self.packageType {
        case .unknown: type = PackageTypeBase.unknown
        case .custom: type = PackageTypeBase.custom
        case .lifetime: type = PackageTypeBase.lifetime
        case .annual: type = PackageTypeBase.annual
        case .sixMonth: type = PackageTypeBase.sixMonth
        case .threeMonth: type = PackageTypeBase.twoMonth
        case .twoMonth: type = PackageTypeBase.twoMonth
        case .monthly: type = PackageTypeBase.monthly
        case .weekly: type = PackageTypeBase.weekly
        }

        return SubscriptionProduct(
            price: self.storeProduct.localizedPriceString,
            title: self.storeProduct.localizedTitle,
            description: self.storeProduct.localizedDescription,
            type: type
       )
    }
}
