import React, { Component } from 'react';
import Alert from 'react-bootstrap/Alert';
import Matches from './components/matches';
import Filter from './components/filter';

class App extends React.Component {

    state = {
        matchList: [],
        hasPhoto: false,
        inContact: false,
        favourite: false,
        lowerBoundDistance: 30,
        upperBoundDistance: 300,
        compatibilityScoreMin: 0,
        compatibilityScoreMax: 0,
        ageMin: 18,
        ageMax: 88,
        heightMin: 120,
        heightMax: 250
    }

    handleCheckboxChange = event => {
        const { name, checked } = event.target
        console.log('name: ' + name)
        console.log('value: ' + checked)
        // console.log('value == on: ' + (value == "on"))
        this.setState({
            // [name]: (value == "on"),
            [name]: checked
        })
        this.fetchMatchList()
    }

    fetchMatchList = () => {
        const beUrl = 'http://localhost:8081/match-making/matches'

        fetch(beUrl, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                    hasPhoto: this.state.hasPhoto,
                    inContact: this.state.inContact,
                    favourite: this.state.favourite
                    // compatibilityScore: {
                    //     min: this.state.compatibilityScoreMin,
                    //     max: this.state.compatibilityScoreMax
                    // },
                    // age: {
                    //     min: this.state.ageMin,
                    //     max: this.state.ageMax
                    // },
                    // height: {
                    //     min: this.state.heightMin,
                    //     max: this.state.heightMax
                    // },
                    // lowerDistanceBoundInKm: this.state.lowerBoundDistance,
                    // upperDistanceBoundInKm: this.state.upperBoundDistance
                })
            })
            .then(res => res.json())
            .then((data) => {
                    console.log(data.matches)
                    this.setState({ matchList: data.matches })
                })
            .catch(console.log)
    }

    componentDidMount() {
        this.fetchMatchList()
    }

    render() {
        return (
            [
                <div><center><h1>Match Making</h1></center></div>,
                <Filter filterOption={this.state} handleCheckboxChange={this.handleCheckboxChange} />,
                <div><center><Alert variant="primary">You future partner is just 1 click away</Alert></center></div>,
                <Matches matchesData={this.state.matchList} />
            ]
        );
    }
}

export default App;