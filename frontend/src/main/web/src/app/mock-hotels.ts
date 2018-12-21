import {Hotel} from "./hotel";

export const HOTELS: Hotel[] = [
  {
    id: 1,
    name: 'The Plaza, New York',
    description: 'Opened in 1907 and designated an official landmark in 1969, The Plaza is arguably the most famous hotel in New York.\n' +
      'Located on Fifth Avenue and Central Park South, the 20-story building is the setting of the 1950s "Eloise" children\'s book series and the backdrop for ' +
      'classic films such as "Funny Girl," "The Great Gatsby" and "Home Alone 2: Lost in New York."\n' +
      'Notable guests have included Liza Minnelli, Eleanor Roosevelt, Miles Davis and The Beatles. The hotel offers weekly tours of its interior to the public,' +
      ' led by an architectural historian.',
    rate: 8.9,
    freeRooms: true,
    imagePath: "/assets/hotel_images/hotel_plaza.jpg"
  },
  {
    id: 2,
    name: 'Hotel Ritz Paris',
    description: 'Famously the headquarters of Coco Chanel, Ernest Hemingway and Ingrid Bergman, the legendary Hotel Ritz Paris has set the standard for luxury hotels since its 1898 opening.\n' +
      'Located by the Tuileries gardens and overlooking Place Vendome, it was declared "the most romantic hotel in the world" by Sophia Loren.\n' +
      'Besides its A-list clientele and silver screen cameos -- it\'s starred in classics like "Funny Face," "Love in the Afternoon" and "How to Steal a Million"' +
      ' -- the hotel is renowned for its lavish belle epoque decor, crystal chandeliers and impeccable white-glove service.',
    rate: 9.0,
    freeRooms: false,
    imagePath: "/assets/hotel_images/hotel_ritz.jpg"
  },
  {
    id: 3,
    name: 'Claridge\'s, London',
    description: 'Opened in 1898, this Mayfair institution has been the London residence of Cary Grant, Audrey Hepburn and Yul Brynner.' +
      'During World War II, it was the haven for countless dignitaries and heads of state.' +
      ' In fact, Suite 212 was declared Yugoslavian territory in June 1945 so that Crown Prince Alexander II could be born on his own country\'s soil.',
    rate: 5.4,
    freeRooms: true,
    imagePath: "/assets/hotel_images/claridges.jpg"
  },
  {
    id: 4,
    name: 'Raffles, Singapore',
    description: 'Named after Stamford Raffles, the founder of Singapore, this opulent, colonial-style hotel had surprisingly humble beginnings: It was originally built as a small 10-room bungalow.\n' +
      'It\'s now arguably the most famous hotel in Asia and, more than 125 years after opening, was declared a national monument.\n' +
      'In addition to hosting literati such as Hemingway, Alfred Hitchcock and Rudyard Kipling (who penned "The Jungle Book" while residing in one of its suites),' +
      ' the hotel is the birthplace of the famed Singapore Sling cocktail.',
    rate: 6.7, freeRooms: false,
    imagePath: "/assets/hotel_images/raffles.jpg"
  },
  {
    id: 5,
    name: 'Taj Mahal Palace, India',
    description: 'Built in 1903, the Taj Mahal Palace is Mumbai\'s first harbor landmark, the site of the first licensed bar in the city ' +
      '(the Harbour Bar, which still stands) and the first hotel in India to have steam elevators.',
    rate: 9.7,
    freeRooms: false,
    imagePath: "/assets/hotel_images/tajmahal.jpg"
  },
  {
    id: 6,
    name: 'Beverly Hills Hotel, Los Angeles',
    description: 'The Beverly Hills Hotel, built in 1912, is as glamorous as its A-list clientele. Guests have included Marilyn Monroe, John Wayne, Richard Burton and Elizabeth Taylor.\n' +
      'In 1948, the hotel\'s exterior was painted its distinctive pink to mirror the color of the sunset; it was later featured on the cover of the Eagles\' "Hotel California" album.\n' +
      'The hotel, located on Sunset Boulevard, was named the first historic landmark in Beverly Hills.',
    rate: 9.3,
    freeRooms: false,
    imagePath: "/assets/hotel_images/beverly_hills.jpg"
  },
  {
    id: 7,
    name: 'Peninsula Hong Kong',
    description: 'Known as the "Grand Dame of the East," the Peninsula Hong Kong is the flagship property of Peninsula Hotels. In fact, ' +
      'the hotel\'s name was inspired by its location at the southern tip of the Kowloon Peninsula.\n' +
      'Since its opening in 1928, the Peninsula has set the standard for luxury in Hong Kong, from its glossy fleet of green Rolls Royce Phantoms to the private jet available for guest use.',
    rate: 6.1,
    freeRooms: false,
    imagePath: "/assets/hotel_images/peninsula.jpg"
  },
  {
    id: 8,
    name: 'The Shelbourne Hotel, Dublin',
    description: 'Located on St. Stephen\'s Green in the heart of Dublin, the lavish Shelbourne Hotel is Ireland\'s oldest and most historic hotel, built in 1824.\n' +
      'In 1916, it was taken over by British troops during the Easter Rebellion, and it served as a hub of military activity during World War I.\n' +
      'In 1922, the Irish Constitution was drawn up in Room 112 (now known as the Constitution Room).',
    rate: 10.0,
    freeRooms: true,
    imagePath: "/assets/hotel_images/shelbourne.jpg"
  },
  {
    id: 9,
    name: 'The Ritz Hotel London',
    description: 'The Ritz Hotel, built in 1906, was London\'s first steel structure and the first hotel in the city to have bathrooms in every guest room.\n' +
      'Over its 109-year history, patrons have included nobles like King Edward VIII and movie stars like Charlie Chaplin.\n' +
      'Winston Churchill, Charles de Gaulle and President Eisenhower also famously used the hotel as a regular meeting point during World War II.',
    rate: 8.9,
    freeRooms: true,
    imagePath: "/assets/hotel_images/ritz.jpg"
  },
  {
    id: 10,
    name: 'New Yorker',
    description: 'The Wyndham New Yorker Hotel is a historic hotel located at 481 Eighth Avenue in New York City, United States. The 43-story Art Deco hotel, opened 1930, is a 1,083-room,' +
      ' mid-priced hotel located in Manhattan\'s Garment District and Hell\'s Kitchen areas, near Pennsylvania Station, Madison Square Garden, Times Square, and the Empire State Building.',
    rate: 6.7,
    freeRooms: false,
    imagePath: "/assets/hotel_images/newyorker.jpg"
  }
];
