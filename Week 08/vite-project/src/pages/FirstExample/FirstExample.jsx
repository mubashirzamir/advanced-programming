import {useEffect, useState} from 'react'

const FirstExample = () => {
    const [count, setCount] = useState(0)
    const [visible, setVisible] = useState(true)

    const logOnce = () => console.log('This will only run once')
    const logEachTime = () => console.log('This will run every time')
    const logCount = () => console.log('The count is: ' + count)
    const logVisible = () => console.log('Visible: ' + visible)

    const cleanup = () => console.log('Cleanup')

    useEffect(() => {
        logOnce()
        return cleanup
    }, [])
    useEffect(logEachTime)
    useEffect(logCount, [count])
    useEffect(logVisible, [visible])

    return (
        <div>
            <h1 onClick={() => setVisible(visible => !visible)}>Example</h1>
            {visible && <button onClick={() => setCount(count + 1)}>
                count: {count}
            </button>}
        </div>
    )
}

export default FirstExample