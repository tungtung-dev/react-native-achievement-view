import { PropTypes } from 'react';
import { requireNativeComponent, View } from 'react-native';

var AchievementView = {
  name: 'AchievementView',
  propTypes: {
    index: PropTypes.number,
    ...View.propTypes // include the default view properties
  },
};

module.exports = requireNativeComponent('AchievementView', AchievementView);
