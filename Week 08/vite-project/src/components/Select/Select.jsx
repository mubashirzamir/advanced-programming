import PropTypes from 'prop-types'

const Select = ({options, value, onChange}) => {
    return <select value={value} onChange={e => onChange(e.target.value)}>
        {options.map((option) => {
            return <option key={option.key || option} value={option.value || option}>{option.title || option}</option>
        })}
    </select>
}

Select.propTypes = {
    options: PropTypes.oneOfType([
        PropTypes.arrayOf(
            PropTypes.shape({
                key: PropTypes.number.isRequired,
                value: PropTypes.string.isRequired,
                title: PropTypes.string.isRequired
            })
        ),
        PropTypes.arrayOf(PropTypes.string),
        PropTypes.arrayOf(PropTypes.number)
    ]),
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired
}

export default Select