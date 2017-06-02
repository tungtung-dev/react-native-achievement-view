//
//  achievementview.m
//  achievementview
//
//  Created by Cash on 6/2/17.
//  Copyright © 2017 TungTung. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "achievementview.h"

@implementation AchievementView : UIView

@synthesize index;

-(id)init
{
    self = [super init];
    index = 1;
    return self;
}

- (void)drawRect:(CGRect)rect
{
    CGRect bounds = rect;
    
    UIImage *i = [UIImage imageNamed:[self linkByIndex]];
    
    UIImage *image = [self drawText:i];
    
    [image drawInRect:bounds];
}

/*
 * Get Image link by index
 */
-(NSString*) linkByIndex
{
    NSString *link = @"achievement_1.png";
    if (index== 1) {
        link = @"achievement_1.png";
    } else if (index == 2) {
        link = @"achievement_2.png";
    } else if (index == 3) {
        link = @"achievement_3.png";
    } else {
        link = @"achievement_normal.png";
    }
    return link;
}

/*
 * Get Color by index
 */
-(UIColor*) colorByIndex
{
    switch (index) {
        case 1:
            return [self colorWithHexString:@"f35e3f"];
        case 2:
            return [self colorWithHexString:@"EABD2D"];
        case 3:
            return [self colorWithHexString:@"54b758"];
        default:
            return [self colorWithHexString:@"46a9ff"];
    }
}


-(UIColor*)colorWithHexString:(NSString*)hex
{
    NSString *cString = [[hex stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
    
    // String should be 6 or 8 characters
    if ([cString length] < 6) return [UIColor grayColor];
    
    // strip 0X if it appears
    if ([cString hasPrefix:@"0X"]) cString = [cString substringFromIndex:2];
    
    if ([cString length] != 6) return  [UIColor grayColor];
    
    // Separate into r, g, b substrings
    NSRange range;
    range.location = 0;
    range.length = 2;
    NSString *rString = [cString substringWithRange:range];
    
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    
    // Scan values
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return [UIColor colorWithRed:((float) r / 255.0f)
                           green:((float) g / 255.0f)
                            blue:((float) b / 255.0f)
                           alpha:1.0f];
}

/*
 * Draw text on image
 */
-(UIImage*) drawText: (UIImage*)  image

{
    NSString * text = [@(index) stringValue];
    UIFont* standardFont = [UIFont fontWithName:@"VNFQuickSand-Bold" size:16];
    //  standardFont.fontName. = @"VNFQuickSand-Bold.ttf";
    
    // Get bound of text
    CGSize bound = [self sizeOfString:text withFont:standardFont];
    
    // bound of Image
    CGSize dest = image.size;
    float hypotenuseBound = [self countHypotenuse:bound];
    float hypotenuseDest = [self countHypotenuse:dest];
    float myScale = hypotenuseDest / (hypotenuseBound * 3);
    int x = (dest.width - (int) (bound.width * myScale)) / 2;
    int y = (dest.height - (int) (bound.height * myScale)) / 2;
    UIGraphicsBeginImageContext(image.size);
    [image drawInRect:CGRectMake(0,0,image.size.width,image.size.height)];
    
    CGRect rect = CGRectMake(x, y , image.size.width, image.size.height);
    [[self colorByIndex] set];
    
    UIFont* scaledFont = [UIFont fontWithName:@"VNFQuickSand-Bold" size:myScale * 16];
    
    NSDictionary* attributes = @{ NSFontAttributeName:scaledFont,
                                  NSForegroundColorAttributeName: [self colorByIndex]};
    [text drawInRect:CGRectIntegral(rect) withAttributes:attributes];
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return newImage;
}

/*
 * Count Hypotenuse with input size
 */
- (CGFloat) countHypotenuse: (CGSize) size
{
    return sqrt(size.width * size.width + size.height* size.height);
}

/*
 * Size of string
 */
- (CGSize)sizeOfString:(NSString *)string withFont:(UIFont *)font
{
    NSDictionary *attributes = [NSDictionary dictionaryWithObjectsAndKeys:font, NSFontAttributeName, nil];
    return [[[NSAttributedString alloc] initWithString:string attributes:attributes] size];
}


@end
