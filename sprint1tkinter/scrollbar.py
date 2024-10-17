import tkinter as tk
from tkinter import scrolledtext

ventana = tk.Tk()
ventana.title("Ventana de scrollbar")
ventana.geometry("200x200")

text_area = scrolledtext.ScrolledText()
text_area.pack()

texto = """
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eu massa vel diam laoreet elementum ac sed felis. Donec pharetra euismod arcu, a maximus turpis semper sit amet. Curabitur vestibulum ante sit amet libero pretium, nec consectetur erat pulvinar. Fusce in bibendum felis, id sodales enim.

Integer lacinia elit nec tellus fringilla, id volutpat metus viverra. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi ultricies quam at dui posuere, id pharetra est luctus. Curabitur eget metus sit amet felis ullamcorper varius. Suspendisse potenti.

Sed aliquet nisi nec orci fermentum, quis bibendum risus malesuada. Vivamus euismod odio eu augue finibus, a hendrerit dui tempus. Ut pulvinar, orci eu dictum bibendum, justo ante lacinia purus, et dapibus magna mauris sit amet ligula. Sed nec urna magna. Nullam aliquam orci vel elit sodales, sed condimentum erat placerat.

Etiam tempor, mauris quis cursus dictum, ipsum sapien lacinia libero, at gravida nunc dolor quis est. Vivamus et vehicula mauris. Nunc malesuada velit odio, id cursus justo sagittis nec. Suspendisse potenti. Integer euismod orci ut quam egestas, id consectetur ipsum varius. Duis dapibus ante ac neque pretium volutpat.

Aliquam erat volutpat. Cras euismod nulla in elit ultricies, et bibendum turpis dictum. Quisque non dui sed purus varius convallis. Phasellus tristique, ligula eu viverra aliquet, dui sem dapibus nunc, a sodales eros felis non libero.
"""
text_area.insert(tk.INSERT, texto)

ventana.mainloop()