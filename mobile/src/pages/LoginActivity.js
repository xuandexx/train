/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React, { Component } from 'react';
import {
    ToolbarAndroid,
    AppRegistry,
    StyleSheet,
    Text,
    View,
    Image,
    TextInput,
    TouchableOpacity
} from 'react-native';
import EditView from '../lib/EditView';
import LoginButton from '../lib/LoginButton';
import LoginSuccess from '../ui/LoginSuccess';
import NetUitl from '../lib/NetUtil';
import FontAwesome, { SolidIcons, RegularIcons, BrandIcons } from 'react-native-fontawesome';
import Icon from 'react-native-vector-icons/FontAwesome';


export default class LoginActivity extends Component {
    constructor(props) {
        super(props);
        this.userName = "";
        this.password = "";
    }

    render() {
        return (
            <View style={LoginStyles.loginview}>
                <View style={{
                    flexDirection: 'row', height: 100, marginTop: 1,
                    justifyContent: 'center',
                    alignItems: 'flex-start',
                }}>
                    <Image source={require('../image/login.png')} />
                </View>
                <View style={{ marginTop: 80 }}>
                    <EditView name='输入用户名/注册手机号' onChangeText={(text) => {
                        this.userName = text;
                    }} />
                    <EditView name='输入密码' onChangeText={(text) => {
                        this.password = text;
                    }} />
                    <LoginButton name='登录' onPressCallback={this.onPressCallback} />


                    <View style={{
                        flexDirection: 'row', height: 100, marginTop: 1,
                        justifyContent: 'center',
                    }}>
                        <Icon name="qq" size={30} color="#52C0FE" />
                        <FontAwesome style={{ fontSize: 32 }} icon={SolidIcons.smile} />
                        <FontAwesome style={{ fontSize: 32 }} icon={BrandIcons.github} />
                        <FontAwesome style={{ fontSize: 32 }} icon={BrandIcons.facebfaook} />
                    </View>

                    <View style={{
                        flexDirection: 'row', height: 100, marginTop: 1,
                        justifyContent: 'center',
                        alignItems: 'flex-start',
                    }}>
                        <Text style={{ color: "#4A90E2", textAlign: 'center', marginTop: 10 }} >忘记密码？</Text>
                        <Text style={{ color: "#4A90E2", textAlign: 'center', marginTop: 10 }} > |  </Text>
                        <Text style={{ color: "#4A90E2", textAlign: 'center', marginTop: 10 }} >忘记密码？</Text>
                    </View>
                </View>
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
        const { navigator } = this.props;
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
            <Text >
                没有帐号
            </Text>
        );
    }
}

const LoginStyles = StyleSheet.create({
    loginview: {
        flex: 1,
        padding: 30,
        backgroundColor: '#ffffff',
    },
});
