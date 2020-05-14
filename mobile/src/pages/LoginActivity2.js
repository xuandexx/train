/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React, {Component} from 'react';
import {
    ToolbarAndroid,
    AppRegistry,
    StyleSheet,
    Text,
    View, StatusBar,
    Image,
    TextInput,
    TouchableOpacity
} from 'react-native';
import EditView from '../lib/EditView';
import LoginButton from '../lib/LoginButton';
import LoginSuccess from '../ui/LoginSuccess';
import NetUitl from '../lib/NetUtil';
import FontAwesome, {SolidIcons, RegularIcons, BrandIcons} from 'react-native-fontawesome';
import Icon from 'react-native-vector-icons/FontAwesome';


export default class LoginActivity extends Component {
    constructor(props) {
        super(props);
        this.userName = "";
        this.password = "";
    }

    render() {
        return (
            <View style={styles.loginview}>
                <View>
                    <StatusBar barStyle="light-content"/>
                </View>
                <Text>我爱你喔~ </Text>
                <Text>我爱你喔~ </Text>
            </View>
        )
    }


    onPressCallback = () => {
        let formData = new FormData();
        formData.append("clientId", "32322");
        formData.append("loginName", this.userName);
        formData.append("pwd", this.password);
        let url = "http://localhost:8080/loginApp";
        NetUitl.postJson(url, formData, (responseText) => {
            alert(responseText);
            this.onLoginSuccess();
        })


    };

    //跳转到第二个页面去
    onLoginSuccess() {
        const {navigator} = this.props;
        if (navigator) {
            navigator.push({
                name: 'LoginSuccess',
                component: LoginSuccess,
            });
        }
    }

}

class loginLineView extends Component {
    render() {
        return (
            <Text>
                没有帐号
            </Text>
        );
    }
}

const styles = StyleSheet.create({
    loginview: {
        backgroundColor: 'red',
        width: 300,
        flex: 1,
        flexDirection: 'row',//横向
        justifyContent: 'flex-start'
    },
    container: {
        flex: 1,
    },
    backgroundImage: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
        width: null,
        height: null,
        //不加这句，就是按照屏幕高度自适应
        //加上这几，就是按照屏幕自适应
        //resizeMode:Image.resizeMode.contain,
        //祛除内部元素的白色背景
        backgroundColor: 'rgba(0,0,0,0)',
    }
});
