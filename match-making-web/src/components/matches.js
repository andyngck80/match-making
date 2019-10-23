import React from 'react'
import Image from 'react-bootstrap/Image'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

const Matches = ({ matchesData }) => {
    return (
        <div>
        {matchesData.map((match) => (
            <div class="card">
                <div class="card-body">
                    <Container>
                    <Row>
                        <Col>
                            <h5 class="card-title">{match.displayName}</h5>
                            <Image src={match.mainPhoto} rounded />
                        </Col>
                        <Col>
                            <h6 class="card-subtitle mb-2 text-muted">{match.jobTitle}</h6>
                            <p class="card-text">{match.city.name}</p>
                        </Col>
                    </Row>
                    </Container>
                </div>
            </div>
        ))}
        </div>
    )
};

export default Matches