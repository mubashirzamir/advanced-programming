import PropTypes from 'prop-types'

const Actor = ({name}) => {
    return <div className="container">
        <h1>{name}</h1>
    </div>
}

Actor.propTypes = {
    name: PropTypes.string.isRequired
}

export default Actor