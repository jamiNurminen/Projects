describe('Blog app', function() {
    beforeEach(function() {
      cy.request('POST', 'http://localhost:3003/api/testing/reset')
      const user = {
        name: 'Jamppa Jokunen',
        username: 'BlogiJami',
        password: 'jemma'
      }
      cy.request('POST', 'http://localhost:3003/api/users', user)
      cy.visit('http://localhost:3000')
    })
  
    it('Login form is shown', function() {
      cy.contains('Log in to application')
    })

    describe('Login',function() {
        it('succeeds with correct credentials', function() {
          cy.get('#username').type('BlogiJami')
          cy.get('#password').type('jemma')
          cy.get('#login-button').click()

          cy.contains('Jamppa Jokunen logged in')
        })
    
        it('fails with wrong credentials', function() {
            cy.get('#username').type('BlogiJami')
            cy.get('#password').type('väärä')
            cy.get('#login-button').click()

            cy.contains('Wrong username or password')
        })
      })

      describe('When logged in', function () {
          beforeEach(function () {
            cy.login({ username: 'BlogiJami', password: 'jemma' })

            cy.contains('new blog').click()
            cy.get('#author').type('Blogin kirjoittaja')
            cy.get('#url').type('https://www.blogi.fi')
            cy.get('#title').type('Ensimmäinen blogi')
            cy.get('#submit-button').click()

          })

          it('A blog can be created', function() {

            cy.contains('new blog').click()
            cy.get('#author').type('Blogin kirjoittaja')
            cy.get('#url').type('https://www.blogi.fi')
            cy.get('#title').type('Toinen Blogi')
            cy.get('#submit-button').click()
            cy.contains('Toinen Blogi Blogin kirjoittaja')
          })

          it('Blog can be liked', function() {
            cy.contains('Ensimmäinen blogi Blogin kirjoittaja')
            cy.contains('View').click()
            cy.contains('Like').click()
            cy.contains('Likes: 1')
          })

          it('Blog can be deleted', function() {
              cy.contains('Ensimmäinen blogi Blogin kirjoittaja')
              cy.contains('delete').click()
              cy.get('html').should('not.contain', 'Ensimmäinen blogi Blogin kirjoittaja')
          })

          it('Most liked blog on top', function() {
            cy.contains('Ensimmäinen blogi Blogin kirjoittaja')
            cy.contains('View').click()
            cy.contains('Like').click()
            cy.contains('Likes: 1')

            cy.contains('new blog').click()
            cy.get('#author').type('Blogin kirjoittaja')
            cy.get('#url').type('https://www.blogi.fi')
            cy.get('#title').type('Toinen Blogi')
            cy.get('#submit-button').click()
            cy.contains('Toinen Blogi Blogin kirjoittaja')

            cy.contains('Toinen Blogi Blogin kirjoittaja')
              .contains('View').click()
            cy.contains('Likes: 1')
            cy.contains('Likes: 0')
          })
      })
  })