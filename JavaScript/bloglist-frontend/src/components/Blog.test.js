import React from 'react'
import '@testing-library/jest-dom/extend-expect'
import { render, fireEvent } from '@testing-library/react'
import Blog from './Blog'

test('Renderin kontentti', () => {
    const blog = {
        title: 'Testi',
        author: 'Tuntematon',
        url: 'https://eioo',
        likes: 10
    }

    const component = render(
        <Blog blog={blog} />
    )

    expect(component.container).toHaveTextContent(
        'Testi'
    )

    expect(component.container).toHaveTextContent(
        'Tuntematon'
    )

    expect(component.container).not.toHaveTextContent(
        'https://eioo'
    )

    expect(component.container).not.toHaveTextContent('likes')

})

test('Url ja like näytetään nappia painamalla', () => {
    const blog = {
        title: 'Testi',
        author: 'Tuntematon',
        url: 'https://eioo',
        likes: 10
    }

    const component = render(
        <Blog blog={blog} />
    )

    const button = component.getByText('View')
    expect(component.container).not.toHaveTextContent(
        'https://eioo'
    )

    expect(component.container).not.toHaveTextContent('likes')

    fireEvent.click(button)

    expect(component.container).toHaveTextContent(
        'https://eioo'
    )

    expect(component.container).toHaveTextContent('Likes')
})

test('Like nappia painamalla tapahtumankäsittelijää kutsutaan kahdesti', () => {
    const blog = {
        title: 'Testi',
        author: 'Tuntematon',
        url: 'https://eioo',
        likes: 10
    }
    const mockHandler = jest.fn()
    const component = render(
        <Blog blog={blog} like={mockHandler} />
    )
    
    const button1 = component.getByText('View')
    fireEvent.click(button1)
    const button2 = component.getByText('Like')
    fireEvent.click(button2)
    fireEvent.click(button2)

    expect(mockHandler.mock.calls).toHaveLength(2)

})