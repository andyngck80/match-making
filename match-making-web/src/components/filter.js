import React from 'react'
import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import InputGroup from 'react-bootstrap/InputGroup'
import FormControl from 'react-bootstrap/FormControl'

class Filter extends React.Component {
    render() {
        const { filterOption, handleCheckboxChange } = this.props

        return (
            <div className="container">
                <Container>
                <Row>
                    <Col>
                    <Form.Group controlId="hasPhoto">
                        <Form.Check type="checkbox" label="has photo" onChange={handleCheckboxChange} name="hasPhoto"/>
                    </Form.Group>
                    </Col>
                    <Col>
                        <InputGroup size="sm" className="mb-3">
                            <InputGroup.Prepend>
                                <InputGroup.Text id="inputGroup-sizing-sm">Compatibility Score</InputGroup.Text>
                            </InputGroup.Prepend>
                            <FormControl aria-label="Small" aria-describedby="inputGroup-sizing-sm" size="sm" placeholder="min"></FormControl>
                            <FormControl aria-label="Small" aria-describedby="inputGroup-sizing-sm" size="sm" placeholder="max"/>
                        </InputGroup>
                    </Col>
                </Row>
                <Row>
                    <Col>
                    <Form.Group controlId="inContact">
                        <Form.Check type="checkbox" label="in contact" onChange={handleCheckboxChange} name="inContact"/>
                    </Form.Group>
                    </Col>
                    <Col>
                        <InputGroup size="sm" className="mb-3">
                            <InputGroup.Prepend>
                                <InputGroup.Text id="inputGroup-sizing-sm">Age</InputGroup.Text>
                            </InputGroup.Prepend>
                            <FormControl aria-label="Small" aria-describedby="inputGroup-sizing-sm" size="sm" placeholder="min"/>
                            <FormControl aria-label="Small" aria-describedby="inputGroup-sizing-sm" size="sm" placeholder="max"/>
                        </InputGroup>
                    </Col>
                </Row>
                <Row>
                    <Col>
                    <Form.Group controlId="isFavourite">
                        <Form.Check type="checkbox" label="is favourite" onChange={handleCheckboxChange} name="favourite"/>
                    </Form.Group>
                    </Col>
                    <Col>
                        <InputGroup size="sm" className="mb-3">
                            <InputGroup.Prepend>
                                <InputGroup.Text id="inputGroup-sizing-sm">Height</InputGroup.Text>
                            </InputGroup.Prepend>
                            <FormControl aria-label="Small" aria-describedby="inputGroup-sizing-sm" size="sm" placeholder="min"/>
                            <FormControl aria-label="Small" aria-describedby="inputGroup-sizing-sm" size="sm" placeholder="max"/>
                        </InputGroup>
                    </Col>
                </Row>
                <Row>
                    <Col></Col>
                    <Col>
                        <InputGroup size="sm" className="mb-1">
                            <InputGroup.Prepend>
                                <InputGroup.Text id="inputGroup-sizing-sm">Distance Lower Bound</InputGroup.Text>
                            </InputGroup.Prepend>
                            <FormControl aria-label="Small" aria-describedby="inputGroup-sizing-sm" size="sm" />
                        </InputGroup>
                        <InputGroup size="sm" className="mb-1">
                            <InputGroup.Prepend>
                                <InputGroup.Text id="inputGroup-sizing-sm">Distance Upper Bound</InputGroup.Text>
                            </InputGroup.Prepend>
                            <FormControl aria-label="Small" aria-describedby="inputGroup-sizing-sm" size="sm" />
                        </InputGroup>
                    </Col>
                </Row>
                </Container>
            </div>
        )
    }
}

export default Filter;