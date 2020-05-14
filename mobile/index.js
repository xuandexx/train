import { AppRegistry, Text, Dimensions } from 'react-native';
import Login from './src/pages/LoginActivity2';
import { name as appName } from './app.json';
import React, { Component } from "react";
import LoginActivity from './src/pages/LoginActivity';


class loginLineView extends Component {
    render() {
        return (
            <Text>
                没有帐号: {Dimensions.get('window').width},{Dimensions.get('window').height},{Dimensions.get('window').scale}
            </Text>
        );
    }
}

AppRegistry.registerComponent(appName, () => LoginActivity);