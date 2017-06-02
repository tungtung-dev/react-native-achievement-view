//
//  RNTMapManager.m
//  Sample
//
//  Created by Cash on 5/27/17.
//  Copyright © 2017 Facebook. All rights reserved.
//

#import <React/RCTViewManager.h>
#import "achievementview.h"

@interface RNTAchievementViewManager : RCTViewManager

@end

@implementation RNTAchievementViewManager

RCT_EXPORT_MODULE()

RCT_EXPORT_VIEW_PROPERTY(index, int)

/*
 * Return view to Javascript module
 */
- (UIView *)view
{
  AchievementView * achievement = [[AchievementView alloc] init];
  return achievement;
}

@end
