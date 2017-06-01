import AchievementViewIos from './AchievementViewIos';
import AchievementViewAndroid from './AchievementViewAndroid';
import { Platform } from 'react-native';

const AchievementView = Platform.OS === 'ios'? AchievementViewIos : AchievementViewAndroid;

export default AchievementView;
