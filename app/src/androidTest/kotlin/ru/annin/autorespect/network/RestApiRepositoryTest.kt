package ru.annin.autorespect.network

import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.test.runner.AndroidJUnit4
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import ru.annin.autorespect.data.repository.RestApiRepository
import ru.annin.autorespect.domain.model.CommentResponse
import ru.annin.autorespect.domain.model.DriverInfoResponse
import ru.annin.autorespect.domain.model.DriverRatingResponse
import ru.annin.autorespect.domain.model.TokenResponse
import ru.annin.autorespect.domain.value.UserRating
import rx.observers.TestSubscriber

/**
 * Тест REST API.
 *
 * @author Pavel Annin.
 */
@RunWith(AndroidJUnit4::class)
class RestApiRepositoryTest : TestCase() {

    companion object {

        lateinit var repository: RestApiRepository

        @JvmStatic
        @BeforeClass
        fun setUpClass() {
            repository = RestApiRepository.apply { token = "user_token" }
        }
    }

    @Before
    public override fun setUp() {
        super.setUp()
    }

    @After
    public override fun tearDown() {
        super.tearDown()
    }

    @Test
    fun registration() {
        val subscriber: TestSubscriber<TokenResponse> = TestSubscriber()

        val login = "test@test.ru"
        val password = "qwerty"
        val userName = "Test Test"

        repository.registration(login, password, userName)
                .subscribe(subscriber)

        // Request
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertTrue(subscriber.onNextEvents.isNotEmpty())

        // Data
        val data = subscriber.onNextEvents[0]
        assertThat(data, notNullValue())
    }

    @Test
    fun authorization() {
        val subscriber: TestSubscriber<TokenResponse> = TestSubscriber()

        val login = "test@test.ru"
        val password = "qwerty"

        repository.authorization(login, password)
                .subscribe(subscriber)

        // Request
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertTrue(subscriber.onNextEvents.isNotEmpty())

        // Data
        val data = subscriber.onNextEvents[0]
        assertThat(data, notNullValue())
    }

    @Test
    fun getDriverInfo() {
        val subscriber: TestSubscriber<DriverInfoResponse> = TestSubscriber()

        val regionCode = 58
        val numberCode = "о000оо"

        repository.getDriverInfo(regionCode, numberCode)
                .subscribe(subscriber)

        // Request
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertTrue(subscriber.onNextEvents.isNotEmpty())

        // Data
        val data = subscriber.onNextEvents[0]
        assertThat(data, notNullValue())
    }

    @Test
    fun sendDriverRating() {
        val subscriber: TestSubscriber<DriverRatingResponse> = TestSubscriber()

        val id = 1L
        val rating = UserRating.LIKE

        repository.sendDriverRating(id, rating)
                .subscribe(subscriber)

        // Request
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertTrue(subscriber.onNextEvents.isNotEmpty())

        // Data
        val data = subscriber.onNextEvents[0]
        assertThat(data, notNullValue())
    }

    @Test
    fun getDriveComments() {
        val subscriber: TestSubscriber<List<CommentResponse>> = TestSubscriber()

        val id = 1L
        val limit = 20
        val offset = 0

        repository.getDriveComments(id, limit, offset)
                .subscribe(subscriber)

        // Request
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertTrue(subscriber.onNextEvents.isNotEmpty())

        // Data
        val data = subscriber.onNextEvents[0]
        assertThat(data, notNullValue())
    }

    @Test
    fun createDriveComment() {
        val subscriber: TestSubscriber<Unit> = TestSubscriber()

        val id = 1L
        val comment = "Test text"

        repository.createDriveComment(id, comment)
                .subscribe(subscriber)

        // Request
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertTrue(subscriber.onNextEvents.isNotEmpty())

        // Data
        val data = subscriber.onNextEvents[0]
        assertThat(data, notNullValue())
    }

    @Test
    fun editDriveComment() {
        val subscriber: TestSubscriber<Unit> = TestSubscriber()

        val id = 1L
        val comment = "Test text"

        repository.editDriveComment(id, comment)
                .subscribe(subscriber)

        // Request
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertTrue(subscriber.onNextEvents.isNotEmpty())

        // Data
        val data = subscriber.onNextEvents[0]
        assertThat(data, notNullValue())
    }

    @Test
    fun removeDriveComment() {
        val subscriber: TestSubscriber<Unit> = TestSubscriber()

        val id = 1L

        repository.removeDriveComment(id)
                .subscribe(subscriber)

        // Request
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertTrue(subscriber.onNextEvents.isNotEmpty())

        // Data
        val data = subscriber.onNextEvents[0]
        assertThat(data, notNullValue())
    }
}