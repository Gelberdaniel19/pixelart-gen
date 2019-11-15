# PixelTree

A pixelart tree generator. Uses a series of sliders in a web interface to control parameters of a tree. Save presets, preview parameters, and download batches of trees.

- [ ] Web interface
  - [x] Navbar with presets
  - [x] Parameter changer
  - [ ] Save a preset from parameter bar
  - [ ] Edit or delete a preset from navbar
  - [ ] Preview a single generation
  - [ ] Preview a batch of generations
  - [ ] Download a preview
  - [ ] Download a batch
- [ ] Backend
  - [ ] BitPlane class
    - [x] Set pixel
    - [x] DrawLine
    - [x] DrawTriangle
    - [x] DrawQuad
    - [x] Fill from a pixel
    - [ ] Copy from another BitPlane
  - [x] PNG Writer class
    - [x] Set pixel
    - [x] Mask BitPlane to color
    - [x] Overlay another image
  - [ ] Tree generation
    - [ ] Trunk and branches
      - [x] As wires
      - [ ] As quads (mainly trapezoids)
      - [ ] As an outline (list of lines)
      - [ ] Get shaded areas on BitPlane
    - [ ] Leaves
      - [ ] TODO