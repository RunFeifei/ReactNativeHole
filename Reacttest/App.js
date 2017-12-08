/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import AndroidReactRefreshLayout from './src/PullRefreshLayout';
import {View,AppRegistry, Dimensions, ScrollView, Text,ListView} from 'react-native';
import React, {Component} from 'react';


const KEY_REFRESH = "key_refresh";
const {width: SCREEN_WIDTH, height: SCREEN_HEIGHT,} = Dimensions.get('window');

export default class App extends Component {
    constructor(props, context) {
        super(props, context);
        var ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        this.state = {
            dataSource: ds.cloneWithRows(['row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', '111111','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', 'row 2','row 1', '111111']),
        };
    }

    onRefreshStart = () => {
        console.log("-->onRefreshStart")
    }
    onRefreshIng = () => {
        console.log("-->onRefreshIng")
    }
    onRefreshEnd = () => {
        console.log("-->onRefreshEnd")
    }

    render() {
        return (

            <View style={{width: SCREEN_WIDTH, height: SCREEN_HEIGHT,flex:1}}>
                <Text
                    style={{
                        width: SCREEN_WIDTH,
                        height: 50,
                        backgroundColor: 'yellow',
                        textAlign: 'center',
                        textAlignVertical: 'center'
                    }}
                    onPress={() => {
                        this.refs[KEY_REFRESH].stopRefresh()
                    }}>
                    stopRefresh
                </Text>

                <AndroidReactRefreshLayout
                    style={{flex:1}}
                    ref={KEY_REFRESH}
                    onRefreshStart={() => {
                        this.onRefreshStart()
                    }}
                    onRefreshIng={() => {
                        this.onRefreshIng()
                    }}
                    onRefreshEnd={() => {
                        this.onRefreshEnd()
                    }}
                >
                    <ListView
                        dataSource={this.state.dataSource}
                        renderRow={(rowData) => <Text>{rowData}</Text>}
                    />
                </AndroidReactRefreshLayout>

                <Text
                    style={{
                        width: SCREEN_WIDTH,
                        height: 50,
                        backgroundColor: 'yellow',
                        textAlign: 'center',
                        textAlignVertical: 'center'
                    }}
                    onPress={() => {
                        this.refs[KEY_REFRESH].stopRefresh()
                    }}>
                    stopRefresh
                </Text>
            </View>
        );
    }
}

AppRegistry.registerComponent('Reacttest', () => App);
