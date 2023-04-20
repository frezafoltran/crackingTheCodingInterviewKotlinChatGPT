class Card(val suit: Suit, val value: Value) {
   enum class Suit { 
       SPADES, HEARTS, CLUBS, DIAMONDS
   }
   enum class Value {
       ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
   }
}

class DeckOfCards {
   private val deck: MutableList<Card> = mutableListOf()

   init {
       for (suit in Card.Suit.values()) {
           for (value in Card.Value.values()) {
               deck.add(Card(suit, value))
           }
       }
   }

   fun shuffle() {
       deck.shuffle()
   }

   fun drawCard(): Card {
       if (deck.isEmpty()) throw IllegalStateException("Deck is empty!")
       return deck.removeAt(deck.lastIndex)
   }
}

abstract class Hand {
   private val cards: MutableList<Card> = mutableListOf()

   fun addCard(card: Card) {
       cards.add(card)
   }

   fun getScore(): Int {
       var score = 0
       var aces = 0
       for (card in cards) {
           when(card.value) {
               Card.Value.ACE -> {
                   aces++
                   score += 11
               }
               Card.Value.JACK, Card.Value.QUEEN, Card.Value.KING -> score += 10
               else -> score += card.value.ordinal + 1
           }
       }
       while (score > 21 && aces > 0) {
           score -= 10
           aces--
       }
       return score
   }

   fun clear() {
       cards.clear()
   }

   override fun toString(): String {
       return cards.joinToString(", ")
   }
}

class BlackjackHand : Hand() { 
   fun isBust(): Boolean {
       return getScore() > 21
   }

   fun isBlackjack(): Boolean {
       return getScore() == 21 && cards.size == 2
   }
}

class DeckOfCardsTest {

   @Test
   fun `deck of cards`() {
       val deck = DeckOfCards()
       assertEquals(52, deck.size())
       deck.shuffle()
       for (i in 1..52) {
           assertNotNull(deck.drawCard())
       }
       assertFailsWith<IllegalStateException> { deck.drawCard() }
   }

   @Test
   fun `blackjack hand`() {
       val hand = BlackjackHand()
       assertEquals(0, hand.getScore())
       hand.addCard(Card(Card.Suit.SPADES, Card.Value.ACE))
       hand.addCard(Card(Card.Suit.HEARTS, Card.Value.KING))
       assertEquals(21, hand.getScore())
       assertTrue(hand.isBlackjack())
       assertFalse(hand.isBust())
       hand.addCard(Card(Card.Suit.CLUBS, Card.Value.FIVE))
       assertEquals(16, hand.getScore())
       assertFalse(hand.isBlackjack())
       assertFalse(hand.isBust())
       hand.addCard(Card(Card.Suit.DIAMONDS, Card.Value.TEN))
       assertEquals(26, hand.getScore())
       assertFalse(hand.isBlackjack())
       assertTrue(hand.isBust())
       hand.clear()
       assertEquals(0, hand.getScore())
   }
}